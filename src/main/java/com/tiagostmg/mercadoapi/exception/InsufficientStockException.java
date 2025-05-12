package com.tiagostmg.mercadoapi.exception;

public class InsufficientStockException extends PurchaseException {
    public InsufficientStockException(int available, int requested) {
        super(String.format("Estoque insuficiente. Disponível: %d, Solicitado: %d", available, requested));
    }
}
