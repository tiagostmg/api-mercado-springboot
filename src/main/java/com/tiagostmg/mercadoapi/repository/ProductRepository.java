package com.tiagostmg.mercadoapi.repository;

import com.tiagostmg.mercadoapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
