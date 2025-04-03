package com.lojaonline.lojaonline.dto;


import lombok.Data;

@Data
public class ProductDTO {
    private String nome;
    private double price;
    private Long quantity;
}
