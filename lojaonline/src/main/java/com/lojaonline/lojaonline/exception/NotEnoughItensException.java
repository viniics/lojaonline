package com.lojaonline.lojaonline.exception;

public class NotEnoughItensException extends RuntimeException {
    private final int currentItems;

    public NotEnoughItensException(String message, int currentItems) {
        super(message);
        this.currentItems = currentItems;
    }

    public int getCurrentItems() {
        return currentItems;
    }
}