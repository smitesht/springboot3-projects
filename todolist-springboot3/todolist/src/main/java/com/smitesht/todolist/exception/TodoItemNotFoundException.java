package com.smitesht.todolist.exception;

public class TodoItemNotFoundException extends RuntimeException{

    public TodoItemNotFoundException(String message) {
        super(message);
    }
}
