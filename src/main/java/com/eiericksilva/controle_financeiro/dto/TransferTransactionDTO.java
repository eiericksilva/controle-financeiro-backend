package com.eiericksilva.controle_financeiro.dto;

import java.math.BigDecimal;
import java.util.Set;
import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.Tag;
import com.eiericksilva.controle_financeiro.enums.TransactionType;

public record TransferTransactionDTO(
        Long id,
        TransactionType transactionType,
        BigDecimal amount,
        String description,
        String observation,
        Boolean isConfirmed,
        Set<Tag> tags,
        Account sourceAccount,
        Account destinationAccount) {
}
