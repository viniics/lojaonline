package com.lojaonline.lojaonline.dto;

import com.lojaonline.lojaonline.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCreatedSuccessfullyResponse {
    private String message;
    private Product product;
}
