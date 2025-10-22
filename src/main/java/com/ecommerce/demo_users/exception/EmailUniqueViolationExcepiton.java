package com.ecommerce.demo_users.exception;

public class EmailUniqueViolationExcepiton extends RuntimeException {

    public EmailUniqueViolationExcepiton(String message) {
        super(message);
    }
}
