package com.eiericksilva.controle_financeiro.exceptions;

import java.math.BigDecimal;

public class InsufficientMinimumValueException extends RuntimeException {
    public InsufficientMinimumValueException() {
        super("The value provided for the 'amount' field must be greater than or equal to 0.01. Please provide a valid amount to continue with the transaction");
    }
}