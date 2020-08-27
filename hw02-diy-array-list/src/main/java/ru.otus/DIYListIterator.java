package ru.otus;

import java.util.ListIterator;

public class DIYListIterator<E> implements ListIterator<E> {

    E[] array;
    int index;

    public DIYListIterator(E[] array) {
        this.array = array;
    }

    public DIYListIterator(int index) {
        this.index = index;
    }

    @Override
    public boolean hasNext() {
        return array.length > index;
    }

    @Override
    public E next() {
        return array[index++];
    }

    @Override
    public boolean hasPrevious() {
        try{
            E elem = array[index - 1];
        } catch (ArrayIndexOutOfBoundsException ex){
            return false;
        }
        return true;
    }

    @Override
    public E previous() {
        return array[index - 1];
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        //TODO: I dont know how to do this yet
    }

    @Override
    public void set(Object o) {
        array[index - 1] = (E) o;
    }

    @Override
    public void add(Object o) {
        array[index] = (E) o;
    }
}
