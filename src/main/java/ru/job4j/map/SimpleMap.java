package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];
    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexForFinal(key);
        if (table[index] == null) {
            table[index] = new MapEntry(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }
    private int indexForFinal(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        return index;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] intermediateTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> tableElement = table[i];
                int index = indexForFinal(tableElement.key);
                intermediateTable[index] = tableElement;
            }
        }
        table = intermediateTable;
    }

    private boolean keysCheck(K key1, K key2) {
        boolean result = false;
        if ((key1 == null && key2 == null) || (key1.hashCode() == key2.hashCode() && key1.equals(key2))) {
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexForFinal(key);
        if (table[index] != null) {
           if ((key != null && table[index].key != null) || (key == null && table[index].key == null)) {
               if (keysCheck(key, table[index].key)) {
                   result = table[index].value;
               }
           }
        }
        return result;
    }
    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexForFinal(key);
        if (table[index] != null) {
            if ((key != null && table[index].key != null) || (key == null && table[index].key == null)) {
                if (keysCheck(key, table[index].key)) {
                    table[index] = null;
                    modCount--;
                    count--;
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while ((index < capacity) && (table[index] == null)) {
                    index++;
                }
                return index < capacity;
            }
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}