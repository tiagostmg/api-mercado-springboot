package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.dto.ProductDTO;
import com.tiagostmg.mercadoapi.entity.Product;
import com.tiagostmg.mercadoapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Cria um novo produto")
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<List<ProductDTO>> create(@Valid @RequestBody ProductDTO productDTO) {
        productService.save(productDTO.toEntity());
        return ResponseEntity.ok(getAll());
    }

    @Operation(summary = "Lista todos os produtos")
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll()
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Lista produto por id")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getById(id);
        return productOptional.map(product -> ResponseEntity.ok(ProductDTO.fromEntity(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um produto existente")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<List<ProductDTO>> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            Product product = productDTO.toEntity();
            product.setId(id);
            productService.save(product);
            return ResponseEntity.ok(getAll());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Exclui um produto")
    @ApiResponse(responseCode = "200", description = "Produto excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ProductDTO>> delete(@PathVariable Long id) {
        if(productService.existsById(id)) {
            productService.delete(id);
            return ResponseEntity.ok(getAll());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
