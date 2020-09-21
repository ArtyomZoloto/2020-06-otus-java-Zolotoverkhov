package ru.otus.atm.exceptions;

import java.util.function.Supplier;

public class NoBanknoteException extends AtmException {
    public NoBanknoteException(String message) {
        super(message);
    }
}
