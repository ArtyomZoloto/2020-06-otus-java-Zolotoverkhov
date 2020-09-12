package ru.otus.atm.result;

/**
 * Перечисление возможных итогов проведения операции
 */
public enum AtmOperationStatus {
    SUCCESS("Успешно"),
    INSUFFICIENT_FUNDS("в банкомате недостаточно денег"),
    NO_EXCHARGE("нет размена");

    private String message;

    AtmOperationStatus(String message) {
        this.message = message;
    }
}
