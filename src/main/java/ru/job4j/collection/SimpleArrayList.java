package ru.job4j.collection;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int modCount = 0;
    private int expectedModCount = 0;
    public int index;
    int i;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
       if (i < size()) {
           container[i] = value;
           i++;
       } else {
           enlargeArray();
           container[i] = value;
           i++;
       }
       modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size());
        T setElements = container[index];
        container[index] = newValue;
        modCount++;
        return setElements;
    }

    @Override
    public T remove(int index) {
       Objects.checkIndex(index, size());
       T remoteElemenr = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size() - index - 1
        );
        container[size() - 1] = null;
        modCount++;
        return remoteElemenr;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return container[index];
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                count++;
            }
        }
        return count;
    }

    public void enlargeArray (){
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }

        };
    }
}