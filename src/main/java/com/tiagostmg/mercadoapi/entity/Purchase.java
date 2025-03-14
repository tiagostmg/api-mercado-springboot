package com.tiagostmg.mercadoapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    private int quantity;
    private LocalDateTime date;

    public Purchase(Client client, Product product, int quantity, LocalDateTime date) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

}
