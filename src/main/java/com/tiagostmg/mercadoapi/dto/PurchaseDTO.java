package com.tiagostmg.mercadoapi.dto;

import com.tiagostmg.mercadoapi.entity.Purchase;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseDTO {
    private Long id;
    private Long clientId;
    private String clientName;
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private LocalDateTime date;

    public static PurchaseDTO fromEntity(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setId(purchase.getId());
        dto.setClientId(purchase.getClient().getId());
        dto.setClientName(purchase.getClient().getName());
        dto.setProductId(purchase.getProduct().getId());
        dto.setProductName(purchase.getProduct().getName());
        dto.setQuantity(purchase.getQuantity());
        dto.setUnitPrice(purchase.getProduct().getPrice());
        dto.setTotalPrice(purchase.getProduct().getPrice() * purchase.getQuantity());
        dto.setDate(purchase.getDate());
        return dto;
    }
}