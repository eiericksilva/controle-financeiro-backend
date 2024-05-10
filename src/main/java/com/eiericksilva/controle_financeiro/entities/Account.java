package com.eiericksilva.controle_financeiro.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account name is required")
    private String name;

    @DecimalMin(value = "0.00")
    private BigDecimal balance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "sourceAccount")
    private final Set<Transaction> outgoingTransactions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "destinationAccount")
    private final Set<Transaction> incomingTransactions = new HashSet<>();

    public Account() {
    }

    public Account(
            Long id,
            String name,
            BigDecimal balance,
            User user) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(4, RoundingMode.HALF_EVEN);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public Set<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setOutgoingTransactions(Transaction outgoingTransaction) {
        this.getOutgoingTransactions().add(outgoingTransaction);
    }

    public void setIncomingTransactions(Transaction incomingTransaction) {
        this.getIncomingTransactions().add(incomingTransaction);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
