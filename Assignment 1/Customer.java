package assignment1;

public class Customer {
    private String name;
    private int balance;
    private Basket basket;

    public Customer(String nameCustomer, int initialBalance){
        this.name = nameCustomer;
        this.balance = initialBalance;
        this.basket = new Basket();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Basket getBasket() {
        return basket;
    }

    public int addFunds(int addBalance) {
        if (addBalance < 0) {
            throw new IllegalArgumentException("The input given for the balance to add is negative. Only additions are permitted");
        } else {
            this.balance = this.balance + addBalance;
            return this.balance;
        }
    }

    public int addToBasket(Reservation reservation){

        if (reservation.reservationName().equals(this.name)){
            int originalLength = basket.getNumOfReservations();
            basket.add(reservation);
            int updatedLength = basket.getNumOfReservations();
            if (updatedLength != originalLength){
                return updatedLength;
            }
            else{
                throw new IllegalArgumentException("The reservation was not successfully added");
            }
        }
        throw new IllegalArgumentException("The reservation names do not match");
    }


    public int addToBasket(Hotel hotel, String roomType, int numberNights, boolean bool){
        HotelReservation newReservation = null;
        if (bool == false){
        newReservation = new HotelReservation(this.name, hotel, roomType, numberNights);
        }
        else{
            newReservation = new BnBReservation(this.name, hotel, roomType, numberNights);
        }
        addToBasket(newReservation);
        return this.basket.getNumOfReservations();
    }


    public int addToBasket(Airport airport1, Airport airport2){
        int previousLength = this.basket.getNumOfReservations();
        FlightReservation newReservation = new FlightReservation(this.name, airport1, airport2);
        addToBasket(newReservation);
        int updatedLength = this.basket.getNumOfReservations();
        if (previousLength != updatedLength){
            return updatedLength;
        }
        else{
            return previousLength;
        }
    }

    public boolean removeFromBasket(Reservation reservation){
        return this.basket.remove(reservation);
    }

    public int checkOut(){
        if (this.balance > this.basket.getTotalCost()){
            this.balance = this.balance - this.basket.getTotalCost();
            this.basket.clear();
            return this.balance; }
        else{
            throw new IllegalStateException("Your balance is not sufficient to proceed with the selected reservations");
        }
    }


}
