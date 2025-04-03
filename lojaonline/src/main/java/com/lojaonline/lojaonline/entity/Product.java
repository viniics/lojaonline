package com.lojaonline.lojaonline.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    private Long id;
    @Column
    private String nome;
    @Column
    private double price;
    @Column
    private Long quantity;
}
