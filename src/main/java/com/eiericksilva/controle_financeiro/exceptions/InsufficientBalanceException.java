package com.eiericksilva.controle_financeiro.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient balance in the account. Please check your available balance and try again.");
    }
}