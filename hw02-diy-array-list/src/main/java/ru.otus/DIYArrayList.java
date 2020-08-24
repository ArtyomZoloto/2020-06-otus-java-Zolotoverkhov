package ru.otus;

import java.util.*;

public class DIYArrayList<T> implements List<T> {


    private T[] innerArray = (T[]) new Object[0];


    @Override
    public int size() {
        return innerArray.length;
    }

    @Override
    public boolean isEmpty() {
        return innerArray.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.stream(innerArray).anyMatch(item -> item.equals(o));
    }

    //TODO: Возможно сделать лучше, чтобы не было утечки памяти из-за зануления ссылок.
    @Override
    public Iterator<T> iterator() {
        return new DIYIterator<T>(innerArray);
    }

    @Override
    public Object[] toArray() {
        return innerArray;
    }

    //TODO: Что делает этот метод????
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        int prevLenght = innerArray.length;
        this.add(innerArray.length,t);
        return prevLenght < innerArray.length;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < innerArray.length; i++) {
            if (innerArray[i].equals(o)) {
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
        for (int i = 0; i <= innerArray.length+1; i++) {
            this.remove(0);
        }
    }

    @Override
    public T get(int index) {
        return innerArray[index];
    }

    @Override
    public T set(int index, T element) {
        T prevElem = innerArray[index];
        innerArray[index] = element;
        return prevElem;
    }

    @Override
    public void add(int index, T element) {
        T[] dest = (T[]) new Object[innerArray.length + 1];
        System.arraycopy(innerArray, 0, dest, 0, index);
        dest[index] = element;
        System.arraycopy(innerArray, index, dest,index +1, dest.length - index - 1);
        innerArray = dest;
    }

    @Override
    public T remove(int index) {

        if (this.size() == 0) {
            throw new UnsupportedOperationException("No elements in list!");
        }

        T[] newArray = (T[]) new Object[innerArray.length - 1];
        System.arraycopy(innerArray, 0, newArray, 0, index);
        System.arraycopy(innerArray, index + 1, newArray, index, innerArray.length - index - 1);
        T obj = innerArray[index];
        innerArray = newArray;
        return obj;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < innerArray.length; i++) {
            if (innerArray[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndexOf = -1;
        for (int i = 0; i < innerArray.length; i++) {
            if (innerArray[i].equals(o)) {
                lastIndexOf = i;
            }
        }
        return lastIndexOf;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYListIterator<>(innerArray);
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
}
