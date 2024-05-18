package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eiericksilva.controle_financeiro.dto.ExpenseTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.IncomeTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.TransferTransactionDTO;
import com.eiericksilva.controle_financeiro.entities.Transaction;
import com.eiericksilva.controle_financeiro.services.TransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /*CREATE*/
    @PostMapping("/income")
    public IncomeTransactionDTO createIncomeTransaction(
            @RequestBody IncomeTransactionDTO incomeTransactionDTO) {
        return transactionService.createIncomeTransaction(incomeTransactionDTO);
    }

    @PostMapping("/expense")
    public ExpenseTransactionDTO createExpenseTransaction(
            @RequestBody ExpenseTransactionDTO expenseTransactionDTO) {
        return transactionService.createExpenseTransaction(expenseTransactionDTO);
    }

    @PostMapping("/tranfer")
    public TransferTransactionDTO createTransferTransaction(
            @RequestBody TransferTransactionDTO transferTransactionDTO) {
        return transactionService.createTransferTransaction(
                transferTransactionDTO);
    }

    /*READ*/
    @GetMapping
    public List<Transaction> findAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction findTransactionById(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }
    /*UPDATE*/

    /*DELETE*/
    @DeleteMapping("/{transactionId}")
    public void delete(@PathVariable Long transactionId) {
        transactionService.deleteTransactionById(transactionId);
    }


}
