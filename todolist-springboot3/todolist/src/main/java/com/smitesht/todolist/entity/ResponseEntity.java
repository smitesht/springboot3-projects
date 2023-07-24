package com.smitesht.todolist.entity;

public class ResponseEntity {

    private int statusCode;
    private Object data = null;
    private String errorMessage = "";


    public ResponseEntity(){

    }

    public ResponseEntity(int statusCode, Object data, String errorMessage) {
        this.statusCode = statusCode;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "statusCode=" + statusCode +
                ", data=" + data +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
