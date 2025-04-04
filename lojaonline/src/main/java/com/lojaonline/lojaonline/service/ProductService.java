package com.lojaonline.lojaonline.service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.entity.Product;
import com.lojaonline.lojaonline.exception.NotEnoughItensException;
import com.lojaonline.lojaonline.exception.ProductIdAlreadyExistsException;
import com.lojaonline.lojaonline.exception.ProductNotFoundException;
import com.lojaonline.lojaonline.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product cadastrarProduto(ProductDTO productDTO) {
        writeLock.lock();
        readLock.lock();
        try {
            if(productRepository.existsById(productDTO.getId())){
                throw new ProductIdAlreadyExistsException("Produto com ID já existente.");
            }
            Product product = new Product(productDTO.getId(),productDTO.getNome(), productDTO.getPrice(), productDTO.getQuantity());
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
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto Não encontrado"));
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
}