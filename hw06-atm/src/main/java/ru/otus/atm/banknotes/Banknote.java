package ru.otus.atm.banknotes;

public abstract class Banknote {
    private BanknoteType type;

    Banknote(BanknoteType type) {
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
