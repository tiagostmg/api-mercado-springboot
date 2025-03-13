package com.tiagostmg.mercadoapi.service;

import com.tiagostmg.mercadoapi.entity.Client;
import com.tiagostmg.mercadoapi.entity.Product;
import com.tiagostmg.mercadoapi.entity.Purchase;
import com.tiagostmg.mercadoapi.repository.ClientRepository;
import com.tiagostmg.mercadoapi.repository.ProductRepository;
import com.tiagostmg.mercadoapi.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(ClientRepository clientRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public String makePurchase(Long clientId, Long productId, int quantity) {
        // Buscar cliente e produto
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Verificar estoque
        if (product.getQuantity() < quantity) {
            return "Estoque insuficiente!";
        }

        // Verificar saldo do cliente
        double totalPrice = product.getPrice() * quantity;
        if (client.getBalance() < totalPrice) {
            return "Saldo insuficiente!";
        }

        // Atualizar saldo do cliente e estoque do produto
        client.setBalance(client.getBalance() - totalPrice);
        product.setQuantity(product.getQuantity() - quantity);

        // Salvar atualizações
        clientRepository.save(client);
        productRepository.save(product);

        // Registrar a compra (Opcional)
        Purchase purchase = new Purchase(client, product, quantity);
        purchaseRepository.save(purchase);

        return "Compra realizada com sucesso!";
    }

}
