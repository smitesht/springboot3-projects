package com.smitesht.todolist.exception;

import com.smitesht.todolist.entity.TodoItemErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoItemExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TodoItemErrorResponse> handleException(TodoItemNotFoundException ex){
        TodoItemErrorResponse error = new TodoItemErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TodoItemErrorResponse> handleException(Exception ex){
        TodoItemErrorResponse error = new TodoItemErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
