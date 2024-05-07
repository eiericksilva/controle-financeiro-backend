package com.eiericksilva.controle_financeiro.entities;

import java.util.ArrayList;
import java.util.List;
import com.eiericksilva.controle_financeiro.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Subcategory> subcategories = new ArrayList<>();

    public Category() {
    }

    public Category(
            Long id, String name,
            CategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void addTransaction(Transaction transaction) {
        this.getTransactions().add(transaction);
        transaction.setCategory(this);
    }

    public void removeTransaction(Transaction transaction) {
        this.getTransactions().remove(transaction);
        transaction.setCategory(null);
    }

    public void addSubcategory(Subcategory subcategory) {
        this.getSubcategories().add(subcategory);
        subcategory.setCategory(this);
    }

    public void removeSubcategory(Subcategory subcategory) {
        this.getSubcategories().remove(subcategory);
        subcategory.setCategory(null);
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
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
