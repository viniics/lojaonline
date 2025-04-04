package com.lojaonline.lojaonline.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseId;
    @Column
    private Long productId;
    @Column
    private int quantity;
    
    public Purchase(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    
}
