package com.msau.opportunitymanagement.Exceptions;

public class CustomExceptions extends  RuntimeException{
    public CustomExceptions() {

    }
    private String message;
    public String getMessage() {
        return message;
    }

    public CustomExceptions(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
