package com.epam.ld.module2.testing.exception;

public final class MessageContentIsEmptyException extends RuntimeException{
    public MessageContentIsEmptyException(String message) {
        super(message);
    }
}
