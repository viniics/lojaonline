package com.lojaonline.lojaonline.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lojaonline.lojaonline.dto.PurchaseDTO;
import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.service.ProductService;

@RestController
public class PurchaseController {
    private final ProductService purchaseService;

    @Autowired
    public PurchaseController(ProductService purchaseService) {
        this.purchaseService = purchaseService;
    }
    //Endpoint para fazer uma compra de produto.
    @PostMapping("/purchase")
    public ResponseEntity<Purchase> purchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.newPurchase(purchaseDTO);
        return ResponseEntity.ok(purchase);
    }
    //Endpoint para gerar um relatorio de todas as vendas feitas no sistema.
    @GetMapping("/sales/report")
    public ResponseEntity<Map<String, Object>> getSalesReport() {
        Map<String, Object> report = purchaseService.generateSalesReport();
        return ResponseEntity.ok(report);
    }

}
