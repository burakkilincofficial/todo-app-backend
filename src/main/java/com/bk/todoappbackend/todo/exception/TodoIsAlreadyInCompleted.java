package com.bk.todoappbackend.todo.exception;

public class TodoIsAlreadyInCompleted extends Exception {
    public TodoIsAlreadyInCompleted(String message) {
        super(message);
    }
}
