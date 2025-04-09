package com.tiagostmg.mercadoapi.repository;

import com.tiagostmg.mercadoapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
