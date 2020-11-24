package ru.otus.util;

import java.time.LocalDateTime;

public class TimeProviderImpl implements TimeProvider {

    @Override
    public boolean isEvenSecond() {
        return LocalDateTime.now().getSecond() % 2 == 0;
    }
}
