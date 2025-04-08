package com.lojaonline.lojaonline.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lojaonline.lojaonline.dto.ProductCreatedSuccessfullyResponse;
import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.dto.StockUpdateDTO;
import com.lojaonline.lojaonline.dto.StockUpdateResponse;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.service.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Endpoint para listar todos os produtos cadastrados no sistema
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }
    //Endpoint que lista informacoes de um produto especifico
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductInfo(@PathVariable Long id) {
        Product response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }
    //Endpoint para cadastrar um produto. Requer o envio de um Body contendo um objeto ProductDTO
    @PostMapping("/products")
    public ResponseEntity<ProductCreatedSuccessfullyResponse> cadastrarProduto(@RequestBody ProductDTO productDTO) {
        Product savedProduct = productService.cadastrarProduto(productDTO);
        ProductCreatedSuccessfullyResponse response = new ProductCreatedSuccessfullyResponse(
                "Produto cadastrado com sucesso.", savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //Endpoint para atualizar o estoque de um produto
    @PutMapping("/products/{id}/stock")
    public ResponseEntity<StockUpdateResponse> atualizarEstoque(
            @PathVariable Long id,
            @RequestBody StockUpdateDTO stockUpdateDTO) {

        Product atualizado = productService.atualizarEstoque(id, stockUpdateDTO.getQuantity());

        StockUpdateResponse response = new StockUpdateResponse(atualizado.getQuantity());

        return ResponseEntity.ok(response);
    }

    



}
