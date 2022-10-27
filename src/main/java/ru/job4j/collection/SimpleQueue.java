package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;
    public T poll() {
        if (outSize == 0) {
            if (inSize == 0) {
                throw new NoSuchElementException();
            }
            while (inSize != 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }
    public void push(T value) {
         in.push(value);
         inSize++;
    }
}