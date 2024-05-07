package com.eiericksilva.controle_financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.entities.Tag;
import com.eiericksilva.controle_financeiro.enums.TransactionType;

public record IncomeTransactionDTO(
                Long id,
                TransactionType transactionType,
                BigDecimal amount,
                Category category,
                Subcategory subcategory,
                LocalDate expiredDate,
                String description,
                String observation,
                Boolean isConfirmed,
                Set<Tag> tags,
                Account destinationAccount) {
}
