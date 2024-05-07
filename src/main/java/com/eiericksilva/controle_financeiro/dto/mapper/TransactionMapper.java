package com.eiericksilva.controle_financeiro.dto.mapper;

import org.springframework.stereotype.Component;

import com.eiericksilva.controle_financeiro.dto.ExpenseTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.IncomeTransactionDTO;
import com.eiericksilva.controle_financeiro.dto.TransferTransactionDTO;
import com.eiericksilva.controle_financeiro.entities.Transaction;

@Component
public class TransactionMapper {

    public IncomeTransactionDTO toIncomeTransactionDTO(Transaction transaction) {
        return new IncomeTransactionDTO(
                transaction.getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getSubcategory(),
                transaction.getExpiredDate(),
                transaction.getDescription(),
                transaction.getObservation(),
                transaction.getIsConfirmed(),
                transaction.getTags(),
                transaction.getDestinationAccount());
    }

    public ExpenseTransactionDTO toExpenseTransactionDTO(Transaction transaction) {
        return new ExpenseTransactionDTO(
                transaction.getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getSubcategory(),
                transaction.getExpiredDate(),
                transaction.getDescription(),
                transaction.getObservation(),
                transaction.getIsConfirmed(),
                transaction.getTags(),
                transaction.getSourceAccount());
    }

    public TransferTransactionDTO toTransferTransactionDTO(Transaction transaction) {
        return new TransferTransactionDTO(
                transaction.getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getObservation(),
                transaction.getIsConfirmed(),
                transaction.getTags(),
                transaction.getSourceAccount(),
                transaction.getDestinationAccount());
    }

    public Transaction incomeDTOtoEntity(IncomeTransactionDTO incomeTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(incomeTransactionDTO.id());
        transaction.setTransactionType(incomeTransactionDTO.transactionType());
        transaction.setAmount(incomeTransactionDTO.amount());
        transaction.setCategory(incomeTransactionDTO.category());
        transaction.setSubcategory(incomeTransactionDTO.subcategory());
        transaction.setExpiredDate(incomeTransactionDTO.expiredDate());
        transaction.setDescription(incomeTransactionDTO.description());
        transaction.setObservation(incomeTransactionDTO.observation());
        transaction.setIsConfirmed(incomeTransactionDTO.isConfirmed());
        transaction.setTags(incomeTransactionDTO.tags());
        transaction.setDestinationAccount(incomeTransactionDTO.destinationAccount());
        return transaction;
    }

    public Transaction expenseDTOtoEntity(ExpenseTransactionDTO expenseTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(expenseTransactionDTO.id());
        transaction.setTransactionType(expenseTransactionDTO.transactionType());
        transaction.setAmount(expenseTransactionDTO.amount());
        transaction.setCategory(expenseTransactionDTO.category());
        transaction.setSubcategory(expenseTransactionDTO.subcategory());
        transaction.setExpiredDate(expenseTransactionDTO.expiredDate());
        transaction.setDescription(expenseTransactionDTO.description());
        transaction.setObservation(expenseTransactionDTO.observation());
        transaction.setIsConfirmed(expenseTransactionDTO.isConfirmed());
        transaction.setTags(expenseTransactionDTO.tags());
        transaction.setDestinationAccount(expenseTransactionDTO.sourceAccount());
        return transaction;
    }

    public Transaction transferDTOtoEntity(TransferTransactionDTO transferTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transferTransactionDTO.id());
        transaction.setTransactionType(transferTransactionDTO.transactionType());
        transaction.setAmount(transferTransactionDTO.amount());
        transaction.setDescription(transferTransactionDTO.description());
        transaction.setObservation(transferTransactionDTO.observation());
        transaction.setIsConfirmed(transferTransactionDTO.isConfirmed());
        transaction.setTags(transferTransactionDTO.tags());
        transaction.setDestinationAccount(transferTransactionDTO.sourceAccount());
        transaction.setDestinationAccount(transferTransactionDTO.destinationAccount());
        return transaction;
    }

}