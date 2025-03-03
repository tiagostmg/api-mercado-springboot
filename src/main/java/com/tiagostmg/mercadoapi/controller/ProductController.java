package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.entity.Product;
import com.tiagostmg.mercadoapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public List<Product> create(@RequestBody Product product) {
        productService.save(product);
        return getAll();
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PutMapping
    public List<Product> update(@RequestBody Product product) {
        productService.save(product);
        return getAll();
    }

    @DeleteMapping("/{id}")
    public List<Product> delete(@PathVariable Long id) {
        productService.delete(id);
        return getAll();
    }

}
