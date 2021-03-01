package ru.otus;

public interface MyGson {
    public String toJson(Object object) throws JsonParseException;
}
