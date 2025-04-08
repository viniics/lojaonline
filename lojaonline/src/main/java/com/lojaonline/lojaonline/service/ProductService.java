package com.lojaonline.lojaonline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.dto.PurchaseDTO;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.entity.Purchase;
import com.lojaonline.lojaonline.exception.NotEnoughItensException;
import com.lojaonline.lojaonline.exception.ProductIdAlreadyExistsException;
import com.lojaonline.lojaonline.exception.ProductNotFoundException;
import com.lojaonline.lojaonline.repository.ProductRepository;
import com.lojaonline.lojaonline.repository.PurchaseRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    @Autowired
    public ProductService(ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Product cadastrarProduto(ProductDTO productDTO) {
        writeLock.lock();
        readLock.lock();
        try {
            if (productRepository.existsById(productDTO.getId())) {
                throw new ProductIdAlreadyExistsException("Produto com ID já existente.");
            }
            Product product = new Product(productDTO.getId(), productDTO.getNome(), productDTO.getPrice(),
                    productDTO.getQuantity());
            return productRepository.save(product);
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }

    }

    public List<Product> getAllProducts() {
        writeLock.lock();
        try {
            return productRepository.findAll();
        } finally {
            writeLock.unlock();
        }

    }

    public Product getProductById(Long id) {
        writeLock.lock();
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Produto Não encontrado"));
        } finally {
            writeLock.unlock();
        }
    }

    public Product sell(Long productId, int quantity) {
        writeLock.lock();
        readLock.lock();
        try {
            Product product = getProductById(productId);
            if (product.getQuantity() - quantity < 0) {
                throw new NotEnoughItensException("Estoque insuficiente!", product.getQuantity());
            }
            product.setQuantity(product.getQuantity() - quantity);
            return productRepository.save(product);
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }
    }

    public Product atualizarEstoque(Long id, int novaQuantidade) {
        writeLock.lock();
        readLock.lock();
        try {
            Product product = getProductById(id);
            product.setQuantity(novaQuantidade);
            return productRepository.save(product);
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }
    }

    public Purchase newPurchase(PurchaseDTO purchaseDTO) {
        writeLock.lock();
        readLock.lock();
        try {
            Long productId = purchaseDTO.getId();
            int quantity = purchaseDTO.getQuantity();
            Product product = sell(productId, quantity);
            Purchase purchase = new Purchase(productId, quantity);
            purchase.setRemainingStock(product.getQuantity());
            return purchaseRepository.save(purchase);
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }

    }

    public Map<String, Object> generateSalesReport() {
        writeLock.lock();
        try {
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
        } finally {
            writeLock.unlock();

        }

    }
}