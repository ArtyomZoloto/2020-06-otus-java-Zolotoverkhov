package ru.otus.util;


public interface TimeProvider {

    /**
     * Сообщает, четная сейчас секунда, или не четная
     * @return true = четная секунда, false = нечетная.
     */
    boolean isEvenSecond();
}
