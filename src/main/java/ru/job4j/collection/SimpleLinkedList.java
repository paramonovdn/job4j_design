package ru.job4j.collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(value, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        }
        return x.item;
    }
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            SimpleLinkedList.Node<E> lastReturned;
            SimpleLinkedList.Node<E> next = first;
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
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = next;
                next = next.next;
                index++;
                return lastReturned.item;
            }
        };
    }
    private static class Node<E> {
        E item;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }


}