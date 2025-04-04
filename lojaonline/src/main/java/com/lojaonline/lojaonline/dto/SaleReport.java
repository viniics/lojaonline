package com.lojaonline.lojaonline.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleReport {
    private int totalSales;
    private List<SalesProductDTO> products;
}
