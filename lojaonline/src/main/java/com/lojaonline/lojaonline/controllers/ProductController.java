package com.lojaonline.lojaonline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.service.ProductService;

@RestController
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        List<Product> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

   @PostMapping("/products")
    public ResponseEntity<Product> cadastrarProduto(
            @RequestParam("nome") String nome,
            @RequestParam("price") double price,
            @RequestParam("quantity") Long quantity) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(nome);
        productDTO.setPrice(price);
        productDTO.setQuantity(quantity);
        
        Product savedProduct = productService.cadastrarProduto(productDTO);
        return ResponseEntity.ok(savedProduct);
    }
}
