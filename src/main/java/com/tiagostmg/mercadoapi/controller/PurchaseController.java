package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.dto.PurchaseDTO;
import com.tiagostmg.mercadoapi.entity.Purchase;
import com.tiagostmg.mercadoapi.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Operation(summary = "Processa uma nova compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou estoque/saldo insuficiente"),
            @ApiResponse(responseCode = "404", description = "Cliente ou produto não encontrado")
    })
    @PostMapping("/{clientId}/{productId}/{quantity}")
    public ResponseEntity<PurchaseDTO> makePurchase(
            @PathVariable Long clientId,
            @PathVariable Long productId,
            @PathVariable int quantity) {

        Purchase purchase = purchaseService.processPurchase(clientId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(PurchaseDTO.fromEntity(purchase));
    }

    @Operation(summary = "Lista todas as compras")
    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<PurchaseDTO> purchases = purchaseService.getAllPurchases().stream()
                .map(PurchaseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(purchases);
    }

    @Operation(summary = "Obtém uma compra pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra encontrada"),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        return ResponseEntity.ok(PurchaseDTO.fromEntity(purchase));
    }
}