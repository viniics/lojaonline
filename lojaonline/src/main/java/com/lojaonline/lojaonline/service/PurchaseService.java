package com.lojaonline.lojaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.exception.NotEnoughItensException;
import com.lojaonline.lojaonline.repository.PurchaseRepository;

@Service
public class PurchaseService {
    private final ProductService productService;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(ProductService productService, PurchaseRepository purchaseRepository) {
        this.productService = productService;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase newPurchase(Long productId, int quantity) {
        productService.sell(productId, quantity);
        return purchaseRepository.save(new Purchase(productId, quantity));      
    }
}
