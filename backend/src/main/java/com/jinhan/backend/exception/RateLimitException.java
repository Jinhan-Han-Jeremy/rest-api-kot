package com.jinhan.backend.exception;


public class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }
}