public class deluxeRoom extends Room {

    private final String roomType;
    private final double roomPrice;
    private final int floor; // all single room in
    private static int numLeft = 3;
    private Customer customer;

    deluxeRoom(String roomNo, boolean roomEmpty, boolean checkInStatus) {
        super(roomNo, roomEmpty, checkInStatus);
        roomType = "Deluxe";
        roomPrice = 120.5;
        floor = 3; // all single room in

    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumLeft() {
        return numLeft;
    }

    @Override
    public void bookRoom(Customer c1) {
        this.customer = c1;

    }

    @Override
    public boolean isRoomEmpty() {
        if (customer.name == "") {
            roomEmpty = true;
            return roomEmpty;
        } else {
            return roomEmpty;
        }
    }

}
