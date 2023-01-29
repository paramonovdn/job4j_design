package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class MyFlatMap<T> implements Iterator<T> {
    private final Iterator<T> data;
    private Iterator<T> cursor = Collections.emptyIterator();


    public MyFlatMap(Iterator<T> data) {
        this.data = data;
    }
    int i = 0;
    @Override
    public boolean hasNext() {
        while (!data.hasNext()) {
            data.next();
        }

        return data.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        System.out.println("next data - " + i);
        i++;
        return data.next();
    }

    public static void main(String[] args) {

        System.out.println("iwqey");




    }
}
