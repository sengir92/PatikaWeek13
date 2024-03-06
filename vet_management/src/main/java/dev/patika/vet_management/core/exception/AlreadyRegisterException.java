package dev.patika.vet_management.core.exception;

public class AlreadyRegisterException extends RuntimeException{
    public AlreadyRegisterException(String message) {
        super(message);
    }
}
