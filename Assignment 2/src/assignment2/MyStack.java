package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E> {
    private MyDoublyLinkedList<E> list;

    public MyStack (){
        this.list = new MyDoublyLinkedList<E>();

    }

    public void push(E input){
        this.list.addLast(input);
    }

    public E pop(){
        if (this.list.size == 0){
            throw new NoSuchElementException("There is no element in the Stack");
        }
        else{
            return this.list.removeLast();
        }
    }
/*
    public MyDoublyLinkedList<E> popList(){
        if (this.list.size == 0){
            throw new NoSuchElementException("There is no element in the Stack");
        }
        else{
            this.list.removeLast();
            return this.list;
        }
    }
*/
    public E peek(){
        if (this.list.size == 0){
            throw new NoSuchElementException("There is no element in the Stack");
        }
        return this.list.peekLast();
    }

    public boolean isEmpty(){
        return (this.list.size == 0);
    }

    public void clear(){
        this.list.clear();
    }

    public int getSize(){
        return this.list.getSize();
    }

}
