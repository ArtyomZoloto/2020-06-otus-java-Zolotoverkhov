package ru.otus.atm.exceptions;

public abstract class AtmException extends Exception{
    public AtmException(String message) {
        super(message);
    }
}
