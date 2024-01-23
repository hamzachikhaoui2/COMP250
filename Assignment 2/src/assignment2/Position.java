package assignment2;

public class Position {
    private int xCoordinate;
    private int yCoordinate;

    public Position(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Position(Position position){
        this.xCoordinate = position.xCoordinate;
        this.yCoordinate = position.yCoordinate;
    }

    public void reset(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void reset(Position position){
        this.xCoordinate = position.xCoordinate;
        this.yCoordinate = position.yCoordinate;
    }

    public static int getDistance(Position position1, Position position2){
        int xDistance = position1.xCoordinate - position2.xCoordinate;
        int yDistance = position1.yCoordinate - position2.yCoordinate;
        int distance = Math.abs((xDistance))+Math.abs((yDistance));
        return distance;
    }

    public int getX(){
        return this.xCoordinate;
    }

    public int getY(){
        return this.yCoordinate;
    }

    public void moveWest(){
        this.xCoordinate --;
    }

    public void moveEast(){
        this.xCoordinate ++;
    }

    public void moveNorth(){
        this.yCoordinate --;
    }

    public void moveSouth(){
        this.yCoordinate ++;
    }

    public boolean equals(Object input){
        if (input instanceof Position){
            if (((Position) input).xCoordinate == this.xCoordinate){
                if (((Position) input).yCoordinate == this.yCoordinate){
                    return true;
                }return false;
            }return false;
        }return false;
    }

}
