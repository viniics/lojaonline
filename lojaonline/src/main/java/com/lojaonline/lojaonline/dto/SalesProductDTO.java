package com.lojaonline.lojaonline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesProductDTO {
    private Long id;
    private String name;
    private int quantitySold;
}
