package com.lojaonline.lojaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.repository.ProductRepository;

@Service
public class PurchaseService {
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Purchase> newPurchase(@RequestParam Long productId, @RequestParam int quantity){
        Purchase purchase = new Purchase(productId, quantity);
        return ResponseEntity.ok(purchase);
    }
}
