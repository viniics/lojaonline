package com.lojaonline.lojaonline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long purchaseId;
    @Column
    private Long productId;
    @Column
    private int quantity;
    @Column
    private int remainingStock = 0;
    
    public Purchase(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    
}
