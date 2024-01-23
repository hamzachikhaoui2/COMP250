package assignment2;

import java.util.Iterator;

public class Caterpillar extends MyDoublyLinkedList<Position> {
    public Caterpillar() {
        Position initialPosition = new Position(7, 7);
        this.add(initialPosition); //Should it be super.add or this.add??
    }

    public Position getHead() {
        return this.peekFirst();
    }

    public void eat(Position input) {
        if ((input.getX() != this.peekFirst().getX()) && (input.getY() != this.peekFirst().getY())) {
            throw new IllegalArgumentException("Head and input are not orthogonally adjacent");
        } else {
            this.addFirst(input);
        }
    }

    public void move(Position input) {
        if ((input.getX() != this.peekFirst().getX()) && (input.getY() != this.peekFirst().getY())) {
            throw new IllegalArgumentException("Head and input are not orthogonally adjacent");
        } else {
            this.addFirst(input);
            this.removeLast();
        }
    }



    public boolean selfCollision(Position input) { //This function might have an issue
        Iterator iterator1 = this.iterator();
        for (int i = 0; i < this.getSize(); i++) {
            if (iterator1.equals(input)) {
                return true;
            }
            else{
                iterator1.next();
            }
        }
      return false;
    }


}