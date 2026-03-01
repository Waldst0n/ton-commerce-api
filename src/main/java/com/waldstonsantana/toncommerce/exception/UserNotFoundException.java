package com.waldstonsantana.toncommerce.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
         super(message);
    }
}
