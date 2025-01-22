/** This class represents a singly linked list.
 * LinkedList.java
 * 
 * Author: Seika Oelschig
 * Created on: 03/12/24
 */

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * LinkedList class: a simple implementation of a singly linked list.
 * @param <T> type of the data
 */


public class LinkedList<T> implements Iterable<T>, Queue<T>{
    Node<T> head;
    Node<T> tail;
    private int size;

    
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 
    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = this.head;
        while (current != null) {
            sb.append(current.getData());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
    
    public void add(T item) {
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(o)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T get(int index) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size - 1) {
            return getLast();
        }
        else {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    public T remove() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        T data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }
    
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(item);
        }
        else {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            if (index ==0) {
                add(item);
                return;
            }
            Node<T> current = head;
            Node<T> newNode = new Node<T>(item);
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size - 1) {
            return removeLast();
        }
        else {
            if (index <0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                return remove();
            }
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            T data = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
            return data;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList<?> other = (LinkedList<?>) o;
        if (other.size() != this.size) {
            return false;
        }
        Node<T> current = head;
        Node<?> otherCurrent = other.head;
        while (current != null) {
            if (!current.getData().equals(otherCurrent.getData())) {
                return false;
            }
            current = current.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        return true;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<T>(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        T data = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(null);
            tail = current;
        }
        size--;
        return data;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        return tail.getData();
    }

    @Override
    public boolean offer(T item) {
        try {
            addLast(item);
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }


    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    public T findMin(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }

        Node<T> current = head;
        T minItem = current.getData();
        while (current != null) {
            if (comparator.compare(minItem, current.getData()) > 0) {
                minItem = current.getData();
            }
            current = current.getNext();
        }
        return minItem;
    }

    public T removeMin(Comparator<T> comparator) {
        if (isEmpty()) {
        return null;
        }

        Node<T> current = head;
        Node<T> minPrev = null;
        T minItem = current.getData();
        Node<T> prev = null;

        while (current.getNext() != null) {
            if (comparator.compare(minItem, current.getNext().getData()) > 0) {
                minItem = current.getNext().getData();
                minPrev = prev;
            }
            prev = current;
            current = current.getNext();
        }
        if (minPrev == null) {
            return remove();
        } 
        else {
            minPrev.setNext(minPrev.getNext().getNext());
            if (minPrev.getNext() == null) {
                tail = minPrev;
            }
            size--;
            return minItem;
        }
    }

    private class LLIterator implements Iterator<T> {
        private Node<T> current;

        public LLIterator(Node<T> head) {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    /**
     * Node class: a single node in a linked list that holds data and a reference to the next node in the list.
     * @param <T> type of the data
     */
    static class Node<T> {
        Node<T> next;
        T item;

        public Node(T item) {
            this.item = item;
            this.next = null;
        }

        public T getData() {
            return this.item;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return this.next;
        }
    }
}