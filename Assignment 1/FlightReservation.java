package assignment1;



public class FlightReservation extends Reservation {
    private Airport departure;
    private Airport arrival;

    public FlightReservation (String name, Airport departure, Airport arrival){
        super(name);
        if (departure.equals(arrival)){
            throw new IllegalArgumentException("The two given airports are the same :( ");
        }
        else{
            this.departure = departure;
            this.arrival = arrival;

        }
    }

    public int getCost(){
        double distance = assignment1.Airport.getDistance (this.departure, this.arrival);
        double airportFees = this.departure.getFees() + this.arrival.getFees();
        int price = (int) Math.ceil((distance*(124.0/167.52))+ 5375 + airportFees) ;
        return price;
    }

    public boolean equals(Object input) {
        if (input instanceof FlightReservation) {
            if (((Reservation) input).reservationName().equals((this.reservationName()))){
            if (((FlightReservation) input).departure == this.departure) {
                if (((FlightReservation) input).arrival == this.arrival) {
                    return true; }
                else{
                return false;}}
            else{
                return false;}}

        }

            return false;

        //throw new NoSuchElementException ("No element imputed.");
    }
}

