package com.tiagostmg.mercadoapi.exception;

public class PurchaseException extends RuntimeException {

    public enum ErrorType {
        PURCHASE_NOT_FOUND,
        INSUFFICIENT_BALANCE,
        INSUFFICIENT_STOCK,
        INVALID_QUANTITY
    }

    private final ErrorType errorType;

    public PurchaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public static PurchaseException purchaseNotFound(Long id) {
        return new PurchaseException(
                ErrorType.PURCHASE_NOT_FOUND,
                "Compra não encontrada com ID: " + id
        );
    }

    public static PurchaseException insufficientBalance(double available, double required) {
        return new PurchaseException(
                ErrorType.INSUFFICIENT_BALANCE,
                String.format("Saldo insuficiente. Disponível: %.2f, Necessário: %.2f", available, required)
        );
    }

    public static PurchaseException insufficientStock(int available, int requested) {
        return new PurchaseException(
                ErrorType.INSUFFICIENT_STOCK,
                String.format("Estoque insuficiente. Disponível: %d, Solicitado: %d", available, requested)
        );
    }

    public static PurchaseException invalidQuantity() {
        return new PurchaseException(
                ErrorType.INVALID_QUANTITY,
                "Quantidade deve ser maior que zero"
        );
    }
}