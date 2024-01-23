package assignment1;

public class Airport {
    private int xCoordinate;
    private int yCoordinate;
    private int airportFees;

    public Airport(int int1, int int2, int int3){
        this.xCoordinate = int1;
        this.yCoordinate = int2;
        this.airportFees = int3;

    }

    private void airportReferences(int[] references){
        if (references.length == 3) {
            this.xCoordinate = references[0];
            this.yCoordinate = references[1];
            this.airportFees = references[2];}
        else{
            System.out.println("references given more input than needed");
        }
    }

    public double getFees(){
        return this.airportFees;
    }

    public static int getDistance (Airport airport1, Airport airport2){
        double xDistance = airport1.xCoordinate - airport2.xCoordinate;
        double yDistance = airport1.yCoordinate - airport2.yCoordinate;
        double distance = Math.sqrt( Math.pow(xDistance,2) + Math.pow(yDistance,2));
        return (int) Math.ceil(distance);
    }


}
