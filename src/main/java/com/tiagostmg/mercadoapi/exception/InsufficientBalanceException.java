package com.tiagostmg.mercadoapi.exception;

public class InsufficientBalanceException extends PurchaseException {
    public InsufficientBalanceException(double available, double required) {
        super(String.format("Saldo insuficiente. Disponível: %.2f, Necessário: %.2f", available, required));
    }
}
