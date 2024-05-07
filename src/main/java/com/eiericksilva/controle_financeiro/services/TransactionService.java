package com.eiericksilva.controle_financeiro.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiericksilva.controle_financeiro.dto.ExpenseTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.IncomeTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.TransferTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.AccountMapper;
import com.eiericksilva.controle_financeiro.dto.mapper.TransactionMapper;
import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.Transaction;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public IncomeTransactionDTO createIncomeTransaction(
            Long destinationAccountId,
            IncomeTransactionDTO incomeTransactionDTO) {

        Account destinationAccount = accountMapper.toEntity(accountService.findAccountById(destinationAccountId));

        destinationAccount.setBalance(destinationAccount.getBalance().add(incomeTransactionDTO.amount()));

        accountService.saveAccount(accountMapper.toDTO(destinationAccount));

        Transaction transaction = transactionMapper.incomeDTOtoEntity(incomeTransactionDTO);
        transaction.setDestinationAccount(destinationAccount);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toIncomeTransactionDTO(savedTransaction);
    }

    public ExpenseTransactionDTO createExpenseTransaction(
            Long sourceAccountId,
            ExpenseTransactionDTO expenseTransactionDTO) {

        Account sourceAccount = accountMapper.toEntity(accountService.findAccountById(sourceAccountId));

        accountService.checkSufficientBalance(sourceAccount, expenseTransactionDTO.amount());

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(expenseTransactionDTO.amount()));

        accountService.saveAccount(accountMapper.toDTO(sourceAccount));

        Transaction transaction = transactionMapper.expenseDTOtoEntity(expenseTransactionDTO);

        transaction.setSourceAccount(sourceAccount);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toExpenseTransactionDTO(savedTransaction);
    }

    public TransferTransactionDTO createTransferTransaction(
            Long sourceAccountId,
            Long destinationAccountId,
            TransferTransactionDTO transferTransactionDTO) {

        Account sourceAccount = accountMapper.toEntity(accountService.findAccountById(sourceAccountId));
        Account destinationAccount = accountMapper.toEntity(accountService.findAccountById(destinationAccountId));
        Transaction transaction = transactionMapper.transferDTOtoEntity(transferTransactionDTO);

        BigDecimal transferValue = transaction.getAmount();

        accountService.checkSufficientBalance(sourceAccount, transferValue);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferValue));
        destinationAccount.setBalance(destinationAccount.getBalance().add(transferValue));

        accountService.saveAccount(accountMapper.toDTO(sourceAccount));
        accountService.saveAccount(accountMapper.toDTO(destinationAccount));

        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);

        return transactionMapper.toTransferTransactionDTO(
                transactionRepository.save(transaction));
    }

}
