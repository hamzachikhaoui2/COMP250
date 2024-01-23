package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
    private DNode head;
    private DNode tail;



    private class DNode {
        private E element;
        private DNode next;
        private DNode prev;
    }


    public void add(E element) {
        if (this.isEmpty()){
            DNode node =  new DNode();
            node.element = element;
            this.head = node;
            this.tail = node;
            node.next = null;
            node.prev = null;
            this.size++;
            return;
        }
        else if (this.size == 1){
            DNode node =  new DNode();
            node.element = element;
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
            this.size ++;
            return;

        }
        else if (this.size >=2){
        DNode node =  new DNode();
        node.element = element;
        node.prev = this.tail;
        this.tail.next = node;
        this.tail = node;
        this.size++;
        return;
    }}

    public E remove(){
        if (this.size == 0){
            throw new NoSuchElementException("We cannot remove elements from an empty array");
        }
        else if (this.size == 1){
            DNode dummyNode = this.head;
            this.size = 0;
            this.head = null;
            this.tail = null;
            this.size--;
            return dummyNode.element;}

        else {
            DNode dummyNode = this.tail;
            tail = tail.prev;
            tail.next.prev = null;
            this.size--;
            return dummyNode.element;}
    }
/*
    public E removeList(){
        if (this.size == 0){
            throw new NoSuchElementException("We cannot remove elements from an empty array");
        }
        else if (this.size == 1){
            DNode dummyNode = this.head;
            this.size = 0;
            this.head = null;
            this.tail = null;
            return (E) super.;}

        else{
            DNode dummyNode = this.tail;
            this.tail.prev.next = null;
            this.tail.prev = this.tail.prev.prev;
            this.tail = null;
            this.size--;
            return (E) dummyNode;}
    }
*/


    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(E input){
        if (this.isEmpty()){
            DNode node =  new DNode();
            node.element = input;
            //this.head = node;
            node.next = null;
            node.prev = null;
            this.head = node;
            this.size++;
            return;
        }
        else if (this.size == 1){
            DNode node =  new DNode();
            node.element = input;
            this.tail = this.head;
            this.head = node;
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size++;
            return;
        }
        else if (this.size >= 2){
        DNode node = new DNode();
        node.element = input;
        node.next = this.head;
        this.head.prev = node;
        node.prev = null;
        this.head = node;
        this.size ++;
        return;
    }}

    public void addLast(E input){ //there is definitely an error here
        this.add(input);
}

    public E removeLast(){
        return this.remove();
    }


    public E removeFirst(){
        if (this.size == 0){
            throw new NoSuchElementException("We cannot remove elements from an empty array");
        }
        else if (this.size == 1){
            DNode dummyNode = this.head;
            this.head = null;
            this.tail = null;
            this.size--;
            return dummyNode.element;}

        else if(this.size ==2 ){
            DNode dummyNode = this.head;
            this.head = this.tail;
            this.tail = null;
            this.size --;
            return dummyNode.element;
        }

        else{
            DNode dummyNode = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return dummyNode.element;
        }
    }

    public E peekFirst(){
        if (this.size == 0){
            throw new NoSuchElementException("There is no element in the list to peek at");
        }
        return this.head.element;
    }

    public E peekLast(){
        if (this.size == 0){
            throw new NoSuchElementException("There is no element in the list to peek at");
        }
        if (this.size == 1){
            return this.head.element;
        }
        return this.tail.element;
    }


    public boolean equals (Object input){
        if (input instanceof MyDoublyLinkedList){
            if(((MyDoublyLinkedList) input).isEmpty() && this.isEmpty()){
                return true;}
            MyDoublyLinkedList<?> x = (MyDoublyLinkedList<?>) input;
            Iterator iterator1 = this.iterator();
            Iterator iterator2 = x.iterator();
            if (this.head.element.equals(x.head.element)){
                while (iterator1.hasNext() && iterator2.hasNext()){
                    Object elementIterator1 = iterator1.next();
                    Object elementIterator2 =iterator2.next();
                    if (elementIterator1.equals(elementIterator2)){
                        continue;}
                else{
                return false;}
                }
                return true;
            }
                return false;
            }
        return false;
    }

    public Iterator<E> iterator() {
        return new DLLIterator();
    }


    private class DLLIterator implements Iterator<E> {
        DNode curr;

        public DLLIterator() {
            this.curr = head;
        }

        public boolean hasNext() {
            return this.curr != null;
        }

        public E next() {
            if (!this.hasNext())
                throw new NoSuchElementException();

            E element = this.curr.element;
            this.curr = this.curr.next;
            return element;
        }
    }
}
