package com.epam.ld.module2.testing.exception;

public final class AddressIsEmptyException extends RuntimeException{
    public AddressIsEmptyException(String message) {
        super(message);
    }
}
