package com.lojaonline.lojaonline.entity;
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
    private Long quantity;
    
    public Product(String nome, double price, Long quantity) {
        this.nome = nome;
        this.price = price;
        this.quantity = quantity;
    }

    
}
