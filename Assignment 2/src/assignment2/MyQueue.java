package assignment2;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyQueue<E> {
    private MyDoublyLinkedList<E> list;

    public MyQueue() {
        this.list = new MyDoublyLinkedList<E>();
    }

    public void enqueue(E input) {
        this.list.add(input);
    }

    public E dequeue() {
        if (this.list.size == 0) {
            throw new NoSuchElementException("There is no element in the Stack");
        }
        return this.list.removeFirst();
    }

    public boolean isEmpty() {
        return (this.list.isEmpty());
    }

    public void clear() {
        this.list.clear();
    }

    public boolean equals(Object input) {
        if (input instanceof MyQueue) {
            return (this.list.equals(((MyQueue<?>) input).list));
        }
        return false;
    }
}


