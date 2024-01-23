package assignment1;

public class BnBReservation extends HotelReservation{
    public BnBReservation (String nameReservation, Hotel hotel, String typeRoom, int numberNights){
        super(nameReservation, hotel, typeRoom, numberNights);
    }

    public int getCost(){
        return (super.getCost()+ 1000*this.getNumOfNights());
    }
}
