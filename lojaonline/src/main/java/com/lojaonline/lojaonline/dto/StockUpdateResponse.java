package com.lojaonline.lojaonline.dto;

import lombok.Data;

@Data
public class StockUpdateResponse {
    private String message = "Estoque atualizado.";
    private int remainingStock;

    public StockUpdateResponse(int remainingStock) {
        this.remainingStock = remainingStock;
    }
}
