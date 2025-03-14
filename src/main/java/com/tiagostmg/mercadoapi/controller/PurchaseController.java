package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.entity.Purchase;
import com.tiagostmg.mercadoapi.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/history")
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }
}
