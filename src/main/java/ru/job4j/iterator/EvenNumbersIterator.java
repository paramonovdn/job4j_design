package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if ((data[index] % 2 == 0) && (index < data.length)) {
                index++;
                return true;
            }
            if (!(data[index] % 2 == 0) && (index < data.length)) {
                index++;
            }

        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = index; i < data.length; i++) {
            if ((data[index] % 2 == 0) && (index < data.length)) {
                index++;
                break;
            }
        }
        return index - 1;
    }



}