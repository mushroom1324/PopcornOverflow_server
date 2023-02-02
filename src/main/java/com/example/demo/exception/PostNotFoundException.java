package com.example.demo.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Could not found the post with id : " + id);
    }
}
