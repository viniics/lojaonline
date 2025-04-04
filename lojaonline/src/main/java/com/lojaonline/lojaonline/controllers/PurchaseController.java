package com.lojaonline.lojaonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.service.ProductService;

@RestController
public class PurchaseController {
    private final ProductService productService;
    
    @Autowired
    public PurchaseController(ProductService productService) {
        this.productService = productService;
    }
    

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> purchase(@RequestParam Long id, @RequestParam int quantity){
        Purchase purchase = new Purchase(id, quantity);
        return ResponseEntity.ok(purchase);
    }
}
