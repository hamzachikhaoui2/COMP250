package assignment2;

public class Region {
    private int minXPosition;
    private int minYPosition;
    private int maxXPosition;
    private int maxYPosition;

    public Region (int minXPosition, int minYPosition, int maxXPosition,int maxYPosition){
        this.minXPosition = minXPosition;
        this.minYPosition = minYPosition;
        this.maxXPosition = maxXPosition;
        this.maxYPosition = maxYPosition;
    }

    public boolean contains(Position input){
        return (this.minXPosition<=input.getX() && input.getX()<=this.maxXPosition &&
                this.minYPosition<=input.getY() && input.getY()<=this.maxYPosition);
    }
}
