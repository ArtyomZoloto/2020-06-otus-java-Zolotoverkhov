package ru.otus;

import java.util.*;

public class DIYArrayList<T> implements List<T> {

    private int size;

    private T[] array;

    public DIYArrayList() {
        this.array = (T[]) new Object[5];
    }

    public DIYArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYIterator<T>(array);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array,size());
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size() + 1 > array.length) {
            grow();
        }
        int prevSize = size();
        array[size++] = t;
        return prevSize < size();
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Object o : c) {
            if (!add((T) o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (Object o : c) {
            this.add(index++, (T) o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (int i = 0; i <= size() + 1; i++) {
            this.remove(0);
        }
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        T prevElem = array[index];
        array[index] = element;
        return prevElem;
    }

    @Override
    public void add(int index, T element) {
        if (array.length + 1 > size()) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size() - index);
        array[index] = element;
    }

    @Override
    public T remove(int index) {

        if (this.size() == 0) {
            throw new UnsupportedOperationException("No elements in list!");
        }

        T obj = get(index);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        return obj;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndexOf = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                lastIndexOf = i;
            }
        }
        return lastIndexOf;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYListIterator<>(array);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new DIYListIterator<T>(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> newList = new DIYArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            newList.add(this.get(i));
        }
        return newList;
    }

    private void grow() {
        T[] dest = (T[]) new Object[array.length + array.length / 2];
        System.arraycopy(array, 0, dest, 0, array.length);
        array = dest;
    }
}
