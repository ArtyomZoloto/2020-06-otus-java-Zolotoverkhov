package ru.otus;

import java.util.Collections;
import java.util.List;

public class ObjectForMessage {
    private final List<String> data;

    public ObjectForMessage(List<String> data) {
        this.data = List.copyOf(data);
    }

    public List<String> getData() {
        return Collections.unmodifiableList(data);
    }
}
