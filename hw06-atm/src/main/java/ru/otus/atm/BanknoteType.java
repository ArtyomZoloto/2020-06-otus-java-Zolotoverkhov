package ru.otus.atm;

public enum BanknoteType {
    B5000(5000),
    B2000(2000),
    B1000(1000),
    B500(500),
    B200(200),
    B100(100);

    BanknoteType(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public static BanknoteType getMaxTypeForAmount(int amount) {
        for (BanknoteType type : BanknoteType.values()) {
            if (amount >= type.getValue()) {
                return type;
            }
        }
        return null;
    }
}
