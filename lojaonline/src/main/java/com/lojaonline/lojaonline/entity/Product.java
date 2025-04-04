package com.lojaonline.lojaonline.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column
    private double price;
    @Column
    private int quantity;
    
    public Product(Long id,String nome, double price, int quantity) {
        this.id = id;
        this.nome = nome;
        this.price = price;
        this.quantity = quantity;
    }

}
