package com.lojaonline.lojaonline.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.exception.ProductNotFoundException;
import com.lojaonline.lojaonline.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product cadastrarProduto(ProductDTO productDTO) {
        Product product = new Product(productDTO.getNome(), productDTO.getPrice(),
                new AtomicInteger(productDTO.getQuantity()));
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Produto Não encontrado"));
    }
}