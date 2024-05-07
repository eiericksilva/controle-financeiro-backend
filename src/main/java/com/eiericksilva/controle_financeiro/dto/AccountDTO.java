package com.eiericksilva.controle_financeiro.dto;

import com.eiericksilva.controle_financeiro.entities.User;

import java.math.BigDecimal;

public record AccountDTO(
                Long id,
                String name,
                BigDecimal balance,
                User user) {
}
