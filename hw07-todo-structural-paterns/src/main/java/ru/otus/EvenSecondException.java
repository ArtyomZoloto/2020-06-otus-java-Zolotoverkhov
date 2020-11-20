package ru.otus;

/**
 * Throws is current second is even.
 */

public class EvenSecondException extends RuntimeException {

    public EvenSecondException() {
        super();
    }

    public EvenSecondException(String message){
        super(message);
    }

    public EvenSecondException(String message, Throwable cause){
        super(message, cause);
    }
}
