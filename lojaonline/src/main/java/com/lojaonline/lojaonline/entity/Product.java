package com.lojaonline.lojaonline.entity;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private double price;
    @Column
    private AtomicInteger quantity;
    
    public Product(String nome, double price, AtomicInteger quantity) {
        this.nome = nome;
        this.price = price;
        this.quantity = quantity;
    }

    
}
