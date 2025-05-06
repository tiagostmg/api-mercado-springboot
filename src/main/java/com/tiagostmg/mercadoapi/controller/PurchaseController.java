package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.entity.Purchase;
import com.tiagostmg.mercadoapi.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Operation(summary = "Realiza a compra de um produto por um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na solicitação (por exemplo, cliente ou produto não encontrado, quantidade inválida)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{clientId}/{productId}/{quantity}")
    public String purchaseProduct(@PathVariable Long clientId, @PathVariable Long productId, @PathVariable int quantity) {
        return purchaseService.makePurchase(clientId, productId, quantity);
    }

    @Operation(summary = "Retorna o histórico de compras")
    @ApiResponse(responseCode = "200", description = "Lista de compras retornada com sucesso")
    @GetMapping("/history")
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }
}
