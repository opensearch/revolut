package com.revolut.service;

public class MoneyTransferException extends RuntimeException {

    public MoneyTransferException(String message) {
        super(message);
    }
}
