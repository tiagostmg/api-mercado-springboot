package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.service.PurchaseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/{clientId}/{productId}/{quantity}")
    public String purchaseProduct(@PathVariable Long clientId, @PathVariable Long productId, @PathVariable int quantity) {
        return purchaseService.makePurchase(clientId, productId, quantity);
    }
}
