package com.eiericksilva.controle_financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eiericksilva.controle_financeiro.entities.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.status = 'ACTIVE' ORDER BY t.id DESC ")
    List<Transaction> findAllTransactionsOrderByDateDesc();
}
