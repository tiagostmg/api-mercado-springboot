package com.tiagostmg.mercadoapi.service;

import com.tiagostmg.mercadoapi.entity.Product;
import com.tiagostmg.mercadoapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
