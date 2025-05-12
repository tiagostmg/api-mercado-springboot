package com.tiagostmg.mercadoapi.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Produto não encontrado com ID: " + id);
    }
}