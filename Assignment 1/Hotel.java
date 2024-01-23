package assignment1;

public class Hotel {
    private String nameHotel;
    private Room[] rooms;

    public Hotel(String name, Room[] arrayRooms) {
        this.nameHotel = name;
        this.rooms = new Room[arrayRooms.length];
        for (int i = 0; i < arrayRooms.length; i++){
            this.rooms[i] = new Room(arrayRooms[i]);

        }


    }

    public String getName(){
        return this.nameHotel; }

    public int reserveRoom(String typeRoom) {
        for (int i = 0; i < rooms.length; i++) {
            if (this.rooms[i].getType().equals(typeRoom)) {
                if (rooms[i].getAvailabilityRoom()) {
                    this.rooms[i].changeAvailability();
                    return this.rooms[i].getPrice();

                }
            }
        }
        throw new IllegalArgumentException("There is no such room available at this time.");
        }



    public boolean cancelRoom(String typeRoom) {
        boolean output = true;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getType() == typeRoom) {
                if (rooms[i].getAvailabilityRoom() == false) {
                    rooms[i].changeAvailability();
                    return (rooms[i].getAvailabilityRoom() == false);
                }
            }
        }
        return false;
    }
}



