package com.lojaonline.lojaonline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojaonline.lojaonline.dto.PurchaseDTO;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.repository.ProductRepository;
import com.lojaonline.lojaonline.repository.PurchaseRepository;

@Service
public class PurchaseService {
    private final ProductService productService;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseService(ProductService productService, PurchaseRepository purchaseRepository,
            ProductRepository productRepository) {
        this.productService = productService;
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    public Purchase newPurchase(PurchaseDTO purchaseDTO) {
        Long productId = purchaseDTO.getId();
        int quantity = purchaseDTO.getQuantity();
        Product product = productService.sell(productId, quantity);
        Purchase purchase = new Purchase(productId, quantity);
        purchase.setRemainingStock(product.getQuantity());
        return purchaseRepository.save(purchase);
    }

    public Map<String, Object> generateSalesReport() {
        List<Purchase> purchases = purchaseRepository.findAll();
        List<Map<String, Object>> productList = new ArrayList<>();
        Map<Long, Integer> salesMap = new HashMap<>();
        int totalSales = 0;

        for (Purchase purchase : purchases) {
            Long productId = purchase.getProductId();
            int quantity = purchase.getQuantity();

            totalSales += quantity;

            if (salesMap.containsKey(productId)) {
                int current = salesMap.get(productId);
                salesMap.put(productId, current + quantity);
            } else {
                salesMap.put(productId, quantity);
            }
        }
        for (Map.Entry<Long, Integer> entry : salesMap.entrySet()) {
            Long id = entry.getKey();
            int quantitySold = entry.getValue();

            Product product = productRepository.findById(id).orElse(null);
            if (product != null) {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("id", product.getId());
                productInfo.put("name", product.getNome());
                productInfo.put("quantitySold", quantitySold);

                productList.add(productInfo);
            }
        }
        Map<String, Object> report = new HashMap<>();
        report.put("totalSales", totalSales);
        report.put("products", productList);
        return report;
    }
}
