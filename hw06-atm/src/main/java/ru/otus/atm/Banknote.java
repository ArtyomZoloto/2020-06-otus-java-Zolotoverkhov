package ru.otus.atm;

import java.util.Objects;

public class Banknote {
    private BanknoteType type;

    public Banknote(BanknoteType type) {
        this.type = type;
    }

    public BanknoteType getType() {
        return type;
    }

    public int getValue() {
        return type.getValue();
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "type=" + type +
                '}';
    }
}
