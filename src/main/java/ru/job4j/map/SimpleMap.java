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
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }
    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }
    private int indexFor(int hash) {
        return (hash == 0) ? 0 : hash & (capacity - 1);
    }
    private void expand() {
        MapEntry<K, V>[] intermediateTable = new MapEntry[capacity];
        intermediateTable = table.clone();
        capacity *= 2;
        table = new MapEntry[capacity];
        for (int i = 0; i < intermediateTable.length; i++) {
            if (intermediateTable[i] != null) {
                MapEntry<K, V> tableElement = intermediateTable[i];
                int hashCode = Objects.hashCode(tableElement.key);
                int hash = hash(hashCode);
                int index = indexFor(hash);
                table[index] = tableElement;
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        if (table[index] != null) {
           if (key != null && table[index].key != null) {
               if (key.hashCode() == table[index].key.hashCode() && key.equals(table[index].key)) {
                   result = table[index].value;
               }
           } else {
               if (key == null && table[index].key == null) {
                   result = table[index].value;
               }
           }
        }
        return result;
    }
    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        if (table[index] != null) {
            if (key != null && table[index].key != null) {
                if (key.hashCode() == table[index].key.hashCode() && key.equals(table[index].key)) {
                    table[index] = null;
                    modCount--;
                    count--;
                    result = true;
                }
            } else {
                if (key == null && table[index].key == null) {
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
                return ((index < capacity) && (table[index] != null));
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