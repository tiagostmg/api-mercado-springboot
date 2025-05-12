package com.tiagostmg.mercadoapi.service;

import com.tiagostmg.mercadoapi.entity.Client;
import com.tiagostmg.mercadoapi.entity.Product;
import com.tiagostmg.mercadoapi.entity.Purchase;
import com.tiagostmg.mercadoapi.exception.*;
import com.tiagostmg.mercadoapi.repository.ClientRepository;
import com.tiagostmg.mercadoapi.repository.ProductRepository;
import com.tiagostmg.mercadoapi.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(ClientRepository clientRepository,
                           ProductRepository productRepository,
                           PurchaseRepository purchaseRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public Purchase processPurchase(Long clientId, Long productId, int quantity) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        validatePurchase(client, product, quantity);

        updateClientBalance(client, product, quantity);
        updateProductStock(product, quantity);

        Purchase purchase = createPurchaseRecord(client, product, quantity);

        return purchaseRepository.save(purchase);
    }

    private void validatePurchase(Client client, Product product, int quantity) {
        if (quantity <= 0) {
            throw PurchaseException.invalidQuantity();
        }

        if (product.getQuantity() < quantity) {
            throw PurchaseException.insufficientStock(product.getQuantity(), quantity);
        }

        double totalPrice = product.getPrice() * quantity;
        if (client.getBalance() < totalPrice) {
            throw PurchaseException.insufficientBalance(client.getBalance(), totalPrice);
        }
    }

    private void updateClientBalance(Client client, Product product, int quantity) {
        double totalPrice = product.getPrice() * quantity;
        client.setBalance(client.getBalance() - totalPrice);
        clientRepository.save(client);
    }

    private void updateProductStock(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    private Purchase createPurchaseRecord(Client client, Product product, int quantity) {
        return new Purchase(client, product, quantity, LocalDateTime.now());
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> PurchaseException.purchaseNotFound(id));
    }
}