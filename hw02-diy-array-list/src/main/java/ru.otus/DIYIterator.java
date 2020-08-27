package ru.otus;

import java.util.Iterator;

public class DIYIterator<T> implements Iterator<T> {

    Object[] array;
    int index;

    public DIYIterator(Object[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return array.length > index;
    }

    @Override
    public T next() {
        return (T) array[index++];
    }

    @Override
    public void remove() {
        array[index] = null;
    }
}
