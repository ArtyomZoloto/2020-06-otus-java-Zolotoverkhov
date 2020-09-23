package ru.otus.atm.result;

/**
 * Перечисление возможных итогов проведения операции
 */
public enum AtmOperationStatus {
    SUCCESS("Успешно"),
    INSUFFICIENT_FUNDS("в банкомате недостаточно денег"),
    NO_BANKNOTES("не нашлось банкон для выдачи"),
    FAILED("Ошибка операции");

    private String message;

    AtmOperationStatus(String message) {
        this.message = message;
    }
}
