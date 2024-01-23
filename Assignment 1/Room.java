package assignment1;

public class Room {
    private String typeRoom;
    private int priceRoom;
    private boolean availability;


    public Room (String typeRoom){
        if (typeRoom.equalsIgnoreCase("double")){
            this.typeRoom = typeRoom;
            this.priceRoom = 9000;
            this.availability = true;
        }

        else if (typeRoom.equalsIgnoreCase("queen"))
        {
            this.typeRoom = typeRoom;
            this.priceRoom = 11000;
            this.availability = true;
        }

        else if (typeRoom.equalsIgnoreCase("king")){
            this.typeRoom = typeRoom;
            this.priceRoom = 15000;
            this.availability = true;
        }

        else{
            throw new IllegalArgumentException("No room of such type can be created.");
        }
    }

    public Room(Room room) {
        this.priceRoom = room.getPrice();
        this.typeRoom = room.getType();
        this.availability = room.getAvailabilityRoom();
    }

    public String getType() { return this.typeRoom;}

    public int getPrice() { return  this.priceRoom;}

    public boolean getAvailabilityRoom() { return this.availability;}

    public void changeAvailability(){
        if (this.availability = false){
            this.availability = true;
        }
        else {
            this.availability = false;
        }
    }

    public static Room findAvailableRoom (Room[] rooms, String typeRoom) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null){
                continue;
            }
            else if (rooms[i].availability == true) {
                if (typeRoom.equalsIgnoreCase(rooms[i].typeRoom)) {
                    return  (rooms[i]);
                }
            }
            }
        return null;}

    public static boolean makeRoomAvailable(Room[] rooms, String typeRoom){
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null){
                continue;
            }
            if ((rooms[i].typeRoom).equalsIgnoreCase(typeRoom)){
                if (rooms[i].availability == false){
                    rooms[i].availability = true;
                    return true;
                }
                }
            }

        return (false);
    }




}
