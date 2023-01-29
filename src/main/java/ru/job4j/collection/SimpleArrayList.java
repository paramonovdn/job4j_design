package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }
    @Override
    public void add(T value) {
       if (size >= container.length) {
           enlargeArray();
       }
       container[size++] = value;
       modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T setElements = get(index);
        container[index] = newValue;
        return setElements;
    }

    @Override
    public T remove(int index) {
       T remoteElemenr = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - index - 1
        );
        container[size - 1] = null;
        modCount++;
        size--;
        return remoteElemenr;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
       return size;
    }

    private void enlargeArray() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}