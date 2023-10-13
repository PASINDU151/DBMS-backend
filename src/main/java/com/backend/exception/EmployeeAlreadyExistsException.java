package com.backend.exception;

public class EmployeeAlreadyExistsException extends Throwable {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}