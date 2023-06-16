import java.util.ArrayList;

public abstract class Room {

    protected String roomNo;

    private String roomType;
    private double roomPrice;
    private int floor;
    private static int numLeft;

    // False room empty = Room not available
    // True room empty = Room available
    protected boolean roomEmpty;
    // False check in status = Customer not yet enter room
    // True check in status = Customer entered room
    protected boolean checkInStatus;

    Room(String roomNo, boolean roomEmpty, boolean checkInStatus) {
        this.roomNo = roomNo;
        this.roomEmpty = roomEmpty;
        this.checkInStatus = checkInStatus;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public abstract int getFloor();

    public abstract String getRoomType();

    public abstract double getRoomPrice();

    public abstract int getNumLeft();

    public boolean getEmpty() {
        return roomEmpty;
    }

    public boolean getCheckin() {
        return checkInStatus;
    }

    public void searchRoom(ArrayList<Room> ca, String no) {
        for (int i = 0; i < ca.size(); i++) {
            if (no.compareTo(ca.get(i).getRoomNo()) == 0) {
            } else {
                System.out.println("This room No not exist");
            }
        }
    }

    public abstract void bookRoom(Customer c1);

    public abstract boolean isRoomEmpty();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Object compareTo(String string) {
        return null;
    }
}
