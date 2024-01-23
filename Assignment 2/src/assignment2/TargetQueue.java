package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> stack;


    public TargetQueue (){
        super();
        this.stack = new MyStack<>();
    }


    public void clear() {
        this.stack.clear();
        super.clear();
    }

    public void addTargets(String input) {
        String num = new String();
        char leftParenthesis = '(';
        char rightParenthesis = ')';
        char comma = ',';
        char period = '.';
        for (int i = 0; i < input.length(); i++) {
            Character character = (Character) (input.charAt(i));

            if (i == (input.length()-1)){
                if (character.equals(comma) || Character.isDigit(character) || character.equals(leftParenthesis) ){
                    throw new IllegalArgumentException("Syntax Error");
                }
            }

            if (character.equals(leftParenthesis)) {
                if (i >=1){
                    Character previousCharacter = (Character) (input.charAt(i-1));
                    if (!previousCharacter.equals(period)){
                    throw new IllegalArgumentException("Syntax Error");
                }}
                if (!this.stack.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Syntax Error");
                }
                this.stack.push("(");



            } else if (Character.isDigit(character)) {
                /*if (i >=1){
                    Character previousCharacter = (Character) (input.charAt(i-1));
                    if (!previousCharacter.equals(leftParenthesis) || !previousCharacter.equals(comma)){
                        throw new IllegalArgumentException("Syntax Error");
                    }}*/
                num = num + character;



            } else if (character.equals(comma)) {
                if (i >=1){
                    Character previousCharacter = (Character) (input.charAt(i-1));
                    Character nextCharacter = (Character) (input.charAt(i+1));
                    if (!Character.isDigit(previousCharacter) || !Character.isDigit(nextCharacter)) {
                        throw new IllegalArgumentException("Syntax Error");
                    }}
                if (num.isEmpty()) {
                    throw new IllegalArgumentException("Syntax Error");
                }
                //if (Character.isDigit(Integer.parseInt(num))) {
                this.stack.push(num);
                this.stack.push(",");
                num = "";

            } else if (character.equals(rightParenthesis)) {
                if (this.stack.peek().equals(",") && this.stack.getSize() == 3) {
                    if (num.isEmpty()) {
                        throw new IllegalArgumentException("Syntax Error");
                    }

                    int yCoordinate = (int) Integer.parseInt(String.valueOf(num));
                    //not sure in num will not be empty already or not.
                    this.stack.pop();
                    int xCoordinate = (int) Integer.parseInt(String.valueOf(this.stack.peek()));
                    Position position = new Position(xCoordinate, yCoordinate);
                    this.enqueue(position);
                    num = "";
                    this.stack.clear();
                    continue; }
                else{
                    throw new IllegalArgumentException("Syntax Error");
                }}

            else if (character.equals(period)) {
                if (!this.stack.isEmpty() ||!num.isEmpty()){
                    throw new IllegalArgumentException("Syntax Error");
                }
                if (i>=1){
                Character previousCharacter = (Character) (input.charAt(i-1));
                if (!previousCharacter.equals(rightParenthesis)){
                    throw new IllegalArgumentException("Syntax Error");
                }
                if (previousCharacter.equals(period)){
                    throw new IllegalArgumentException("Syntax Error");
                }
                this.stack.clear();}
                else{
                    this.stack.clear();
                }
                continue;
            }

            else{
                throw new IllegalArgumentException("Syntax Error");
            }



            }
            if (!this.stack.isEmpty() || !num.isEmpty()) {
                throw new IllegalArgumentException("Syntax Error");
            }

        }
    }








