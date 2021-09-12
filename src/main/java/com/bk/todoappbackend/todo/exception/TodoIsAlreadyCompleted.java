package com.bk.todoappbackend.todo.exception;

public class TodoIsAlreadyCompleted extends Exception {
    public TodoIsAlreadyCompleted(String message) {
        super(message);
    }
}
