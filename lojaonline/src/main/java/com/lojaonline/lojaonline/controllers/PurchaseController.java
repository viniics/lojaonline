package com.lojaonline.lojaonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.service.ProductService;
import com.lojaonline.lojaonline.service.PurchaseService;

@RestController
public class PurchaseController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(ProductService productService, PurchaseService purchaseService) {
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> purchase(@RequestParam Long id, @RequestParam int quantity) {
        Purchase purchase = purchaseService.newPurchase(id, quantity);
        return ResponseEntity.ok(purchase);
    }
}
