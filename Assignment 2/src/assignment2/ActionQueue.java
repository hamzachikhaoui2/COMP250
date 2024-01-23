package assignment2;

import org.junit.platform.commons.util.StringUtils;

import java.util.Queue;
import java.util.Stack;

public class ActionQueue extends MyQueue<Direction> {
    private MyStack<String> stack;
    //private MyStack<Direction> directions;
    private Direction N = Direction.NORTH;
    private Direction S = Direction.SOUTH;
    private Direction E = Direction.EAST;
    private Direction W = Direction.WEST;


    public ActionQueue() {
        super();
        this.stack = new MyStack<>();
    }

    public void clear() {
        this.stack.clear();
        super.clear();
    }

    public void loadFromEncodedString(String input) {
        String num = new String();
        int number = 0;
        String directions= new String();
        char leftBracket = '[';
        char rightBracket = ']';
        MyStack<String> stringStack = new MyStack<>();
        MyStack<Integer> integerStack = new MyStack<>();
        for (int i = 0; i < input.length(); i++) {
            Character character = (Character) (input.charAt(i));

            if (Character.isDigit(character)) { //should be deleted
                if (number != 0) {
                    throw new IllegalArgumentException("Syntax Error");
                }//until

                if (i >= 1 && num.isEmpty()) {
                    Character previousCharacter = (Character) (input.charAt(i - 1));
                    if (previousCharacter.equals('N') || previousCharacter.equals('S') || previousCharacter.equals('W')
                            || previousCharacter.equals('E')) {
                        for (int k = 0; k < directions.length(); k++) {
                            if (directions.charAt(k) == 'N') {
                                this.enqueue(N);
                            }
                            if (directions.charAt(k) == 'S') {
                                this.enqueue(S);
                            }
                            if (directions.charAt(k) == 'W') {
                                this.enqueue(W);
                            }
                            if (directions.charAt(k) == 'E') {
                                this.enqueue(E);
                            }
                        }
                    }
                }
                num = num + character;


            } else if (character.equals(leftBracket)) {
                if (i >= 1) {
                    Character previousCharacter = (Character) (input.charAt(i - 1));
                    if (!Character.isDigit(previousCharacter)) {
                        throw new IllegalArgumentException("Invalid Syntax");
                    }
                }

                this.stack.push("[");
                number = Integer.parseInt(num);
            } else if (character.equals('N') || character.equals('S') || character.equals('W')
                    || character.equals('E')) {
                //if (number > 0) {
                if (character.equals('N')) {
                    directions = directions + "N";
                }
                if (character.equals('S')) {
                    directions = directions + "S";
                }
                if (character.equals('E')) {
                    directions = directions + "E";
                }
                if (character.equals('W')) {
                    directions = directions + "W";
                }
                //  }

                if (directions.length() == input.length()) {
                    for (int k = 0; k < directions.length(); k++) {
                        if (directions.charAt(k) == 'N') {
                            this.enqueue(N);
                        }
                        if (directions.charAt(k) == 'S') {
                            this.enqueue(S);
                        }
                        if (directions.charAt(k) == 'W') {
                            this.enqueue(W);
                        }
                        if (directions.charAt(k) == 'E') {
                            this.enqueue(E);
                        }
                    }
                }
            } else if (!character.equals("N") && !character.equals("S") && !character.equals("W")
                    && !character.equals("E") && !character.equals(leftBracket) && !character.equals(rightBracket)
                    && !Character.isDigit(character)) {
                throw new IllegalArgumentException("Invalid Syntax");
            } else if (character.equals(rightBracket)) {
                if (this.stack.isEmpty()) {
                    throw new IllegalArgumentException("Syntax Error");
                }
                String lastPop = this.stack.pop();
                if (lastPop.equals("]")) {
                    throw new IllegalArgumentException("Syntax Error");
                }
                if (lastPop.equals("[") && directions.isEmpty()) {
                    throw new IllegalArgumentException("Syntax Error");
                }


                number = Integer.parseInt(num);

                for (int j = 0; j < number; j++) {
                    for (int k = 0; k < directions.length(); k++) {
                        if (directions.charAt(k) == 'N') {
                            this.enqueue(N);
                        }
                        if (directions.charAt(k) == 'S') {
                            this.enqueue(S);
                        }
                        if (directions.charAt(k) == 'W') {
                            this.enqueue(W);
                        }
                        if (directions.charAt(k) == 'E') {
                            this.enqueue(E);
                        }
                    }
                }
                this.stack.clear();
                number = 0;
                num = "";
                directions = "";
            }
        }}}/*
            String newInput = new String();
            int numberIterations = Integer.parseInt(String.valueOf(num.charAt(num.length())));
            for (int p = 0; p <= numberIterations; p++){
                newInput = input.substring(i,input.length());
                loadFromEncodedString(newInput);
            }}}}

            /*
            if (num.length() > 1 && i == input.length() - 1) {
                //stringStack.push(input);
                for (int m = 0; m < num.length(); m++) {
                    int numberIterations = Integer.parseInt(String.valueOf(num.charAt(m)));
                    integerStack.push(numberIterations);}
                for (int r = 0; r < integerStack.getSize(); r++) {
                        Character characterR = (Character) (input.charAt(r));
                        Integer iter = integerStack.pop();
                        for (int p = 0; p <= iter; p++){
                            loadFromEncodedString(input.substring(i,input.length()-1));

                        }
                    }
                }


        }}}*/



/*
    public void loadFromEncodedString1(String input) {
        int i = 0;
        char leftBracket = '[';
        char rightBracket = ']';
        int num = 0;
        Stack<Integer> stackInteger = new Stack<>();
        while (i < input.length()) {
            Character character = (Character) (input.charAt(i));
            if (Character.isDigit(character)) {
                int numStart = i;
                while (i < input.length() && Character.isDigit(character)) {
                    i++;
                }
                num = Integer.parseInt(input.substring(numStart, i));
                if (i < input.length() && character.equals(leftBracket)) {
                    stackInteger.push(i); // push start index of substring onto stack
                } else {
                    throw new IllegalArgumentException("Invalid syntax");
                }
            } else if (character.equals(rightBracket)) {
                if (stackInteger.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                int start = stackInteger.pop();
                String directions = input.substring(start + 1, i);
                TargetQueue queue = new TargetQueue();
                queue.addTargets(directions);
                for (int j = 0; j < num; j++) {
                    if (directions.charAt(j) == 'N') {
                        this.enqueue(N);}
                    if (directions.charAt(j) == 'S') {
                        this.enqueue(S);}
                    if (directions.charAt(j) == 'W') {
                        this.enqueue(W);}
                    if (directions.charAt(j) == 'E') {
                        this.enqueue(E);}

                }
                num = 0;
                i++;
            } else if (character.equals('N')||character.equals('S') || character.equals('E') || character.equals('W')) {
                this.enqueue(Direction.valueOf(Character.toString(character)));
                i++;
            } else {
                throw new IllegalArgumentException("Invalid syntax");
            }
        }
        if (!stackInteger.isEmpty()) {
            throw new IllegalArgumentException("Invalid syntax");
        }
    }


}
*/