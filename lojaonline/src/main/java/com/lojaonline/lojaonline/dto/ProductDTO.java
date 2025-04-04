package com.lojaonline.lojaonline.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String nome;
    private double price;
    private int quantity;
}
