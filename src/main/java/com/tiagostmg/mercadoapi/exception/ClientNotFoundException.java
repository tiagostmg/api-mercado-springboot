package com.tiagostmg.mercadoapi.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Cliente não encontrado com ID: " + id);
    }
}