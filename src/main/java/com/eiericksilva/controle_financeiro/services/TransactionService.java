package com.eiericksilva.controle_financeiro.services;

import com.eiericksilva.controle_financeiro.dto.ExpenseTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.IncomeTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.TransferTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.AccountMapper;
import com.eiericksilva.controle_financeiro.dto.mapper.TransactionMapper;
import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.Transaction;
import com.eiericksilva.controle_financeiro.exceptions.InsufficientMinimumValueException;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    /*CREATE*/
    public IncomeTransactionDTO createIncomeTransaction(IncomeTransactionDTO incomeTransactionDTO) {
        BigDecimal amount = incomeTransactionDTO.amount();

        verifyIsInsufficientMinimumValueAmount(amount);

        Account destinationAccount = accountMapper.toEntity(accountService.findAccountById(incomeTransactionDTO.destinationAccount().getId()));

        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountService.saveAccount(accountMapper.toDTO(destinationAccount));

        Transaction transaction = transactionMapper.incomeDTOtoEntity(incomeTransactionDTO);
        transaction.setDestinationAccount(destinationAccount);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toIncomeTransactionDTO(savedTransaction);
    }

    public ExpenseTransactionDTO createExpenseTransaction(ExpenseTransactionDTO expenseTransactionDTO) {
        BigDecimal amount = expenseTransactionDTO.amount();

        Account sourceAccount = accountMapper.toEntity(accountService.findAccountById(expenseTransactionDTO.sourceAccount().getId()));


        accountService.checkSufficientBalance(sourceAccount, amount);
        verifyIsInsufficientMinimumValueAmount(amount);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));

        accountService.saveAccount(accountMapper.toDTO(sourceAccount));

        Transaction transaction = transactionMapper.expenseDTOtoEntity(expenseTransactionDTO);

        transaction.setSourceAccount(sourceAccount);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toExpenseTransactionDTO(savedTransaction);
    }

    public TransferTransactionDTO createTransferTransaction(TransferTransactionDTO transferTransactionDTO) {
        Account sourceAccount = accountMapper.toEntity(accountService.findAccountById(transferTransactionDTO.sourceAccount().getId()));
        Account destinationAccount = accountMapper.toEntity(accountService.findAccountById(transferTransactionDTO.destinationAccount().getId()));
        Transaction transaction = transactionMapper.transferDTOtoEntity(transferTransactionDTO);

        BigDecimal amount = transaction.getAmount();

        verifyIsInsufficientMinimumValueAmount(amount);

        accountService.checkSufficientBalance(sourceAccount, amount);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountService.saveAccount(accountMapper.toDTO(sourceAccount));
        accountService.saveAccount(accountMapper.toDTO(destinationAccount));

        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);

        return transactionMapper.toTransferTransactionDTO(transactionRepository.save(transaction));
    }

    /*READ*/
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /*UPDATE*/
    /*DELETE*/
    public void deleteTransactionById(Long transactionId) {
        transactionRepository.delete(
                transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException(transactionId))
        );
    }

    /*AUXILIARES*/
    public void verifyIsInsufficientMinimumValueAmount(BigDecimal amount) {
        BigDecimal minimumValue = BigDecimal.valueOf(0.01);

        if (amount.compareTo(minimumValue) < 0) {
            throw new InsufficientMinimumValueException();
        }
    }
}
