package assignment1;


public class Basket {
    private Reservation[] reservations;
    private int size;

    public Basket() {
        this.reservations = new Reservation[1];
        this.size = 0;
    }

    public void resize() {
        Reservation[] resizedReservations = new Reservation[Basket.this.reservations.length * 2];
        for (int i = 0; i < size; i++) {
            resizedReservations[i] = this.reservations[i];
        }
        reservations = resizedReservations;
    }

    public int add(Reservation reservation) {
        if (this.reservations.length == size){
            resize(); }
        reservations[size] = reservation;
        this.size = this.size+1;
        return this.size;
        }


    public Reservation[] getProducts() {
        Reservation[] copyReservations = new Reservation[reservations.length];
        for (int i = 0; i < size; i++) {
            copyReservations[i] = reservations[i];
        }
        return copyReservations;
    }

    public boolean remove(Reservation reservation){
        for (int i = 0; i < size; i++) {
            if (reservations[i].equals(reservation)){
                if (reservations.length <= 1){
                    this.reservations = null;
                    this.size = 0;
                }
                else{
                for (int j = i; j < size-2; j++) {
                    reservations[j] = reservations[j+1];}
                    reservations[size-1] = null;
                    this.size --;}

                return true;}
            }

        return false;

    }

    public void clear(){
        this.reservations = new Reservation[0];
        this.size = 0;
    }

    public int getNumOfReservations(){
        return this.size;//or this.reservations.length??
    }

    public int getTotalCost(){
        int totalCost = 0;
        for (int i = 0; i < size; i++){
            totalCost = totalCost + reservations[i].getCost();
        }
        return totalCost;
    }

}
