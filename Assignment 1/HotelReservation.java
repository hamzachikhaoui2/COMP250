package assignment1;

public class HotelReservation extends Reservation{
    private Hotel hotel;
    private String typeRoom;
    private int numberNights;
    private int priceTypeRoom;

    public HotelReservation(String name, Hotel hotel, String typeRoom, int numberNights){
        super(name);
        this.hotel = hotel;
        this.typeRoom = typeRoom;
        this.numberNights = numberNights;
        this.priceTypeRoom = hotel.reserveRoom(typeRoom); //not sure if this is actually enough to reserves a room in the hotel.

    }


    public int getNumOfNights() {
        return this.numberNights;
    }

    public int getCost(){
        return (this.numberNights*this.priceTypeRoom);
    }

    public boolean equals(Object input) {
        if (input instanceof HotelReservation) {
            if (super.reservationName().equals((((Reservation) input).reservationName()))){
                if (((HotelReservation) input).hotel.equals((this.hotel))) {
                    if (((HotelReservation) input).typeRoom.equals((this.typeRoom))){
                        if (this.numberNights == ((HotelReservation) input).getNumOfNights()) {
                             if (((HotelReservation) input).getCost() == (this.getCost())) {
                            return true; }
                     else {
                        return false;
                    }}
                } else {
                    return false;
                }}
             else {
                return false;
            }}
         else {
            return false;
        }}
        else{
            return false;
        }

    return false;}

}
