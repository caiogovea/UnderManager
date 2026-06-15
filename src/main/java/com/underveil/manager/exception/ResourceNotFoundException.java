package com.underveil.manager.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String     message) {
            super(message);
    }
}
