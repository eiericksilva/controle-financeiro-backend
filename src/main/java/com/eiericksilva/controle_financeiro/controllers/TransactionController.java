package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eiericksilva.controle_financeiro.dto.ExpenseTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.IncomeTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.TransferTransactionDTO;
import com.eiericksilva.controle_financeiro.entities.Transaction;
import com.eiericksilva.controle_financeiro.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping(value = "/{id}")
    public Transaction findTransactionById(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping("/{destinationAccountId}/income")
    public IncomeTransactionDTO createIncomeTransaction(
            @PathVariable Long destinationAccountId,
            @RequestBody IncomeTransactionDTO incomeTransactionDTO) {
        return transactionService.createIncomeTransaction(destinationAccountId, incomeTransactionDTO);
    }

    @PostMapping("/{sourceAccountId}/expense")
    public ExpenseTransactionDTO createExpenseTransaction(
            @PathVariable Long sourceAccountId,
            @RequestBody ExpenseTransactionDTO expenseTransactionDTO) {
        return transactionService.createExpenseTransaction(sourceAccountId, expenseTransactionDTO);
    }

    @PostMapping("/{sourceAccountId}/{destinationAccountId}/tranfer")
    public TransferTransactionDTO createTransferTransaction(
            @PathVariable Long sourceAccountId,
            @PathVariable Long destinationAccountId,
            @RequestBody TransferTransactionDTO transferTransactionDTO) {
        return transactionService.createTransferTransaction(sourceAccountId, destinationAccountId,
                transferTransactionDTO);
    }
}
