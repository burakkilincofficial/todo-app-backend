package com.bk.todoappbackend.user.exception;

public class UserNameAlreadyExistException extends Exception {
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
