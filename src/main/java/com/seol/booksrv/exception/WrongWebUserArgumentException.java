package com.seol.booksrv.exception;

public class WrongWebUserArgumentException extends RuntimeException {
    public WrongWebUserArgumentException(String message) {
        super(message);
    }
}
