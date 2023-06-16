import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    private String hotel_name;
    private String address;
    private String desc;
    static Room[] roomList = new Room[] {
            new singleRoom("101", false, true),
            new singleRoom("102", false, false),
            new singleRoom("103", true, false),
            new singleRoom("104", true, false),
            new singleRoom("105", true, false),

            new doubleRoom("106", true, false),
            new doubleRoom("107", true, false),
            new doubleRoom("108", true, false),
            new doubleRoom("109", true, false),
            new doubleRoom("110", true, false),

            new deluxeRoom("111", true, false),
            new deluxeRoom("112", true, false)
    };

    public ArrayList<Payment> paymentList = new ArrayList<Payment>();

    public void addPayment(Payment p) {
        paymentList.add(p);
    }

    public ArrayList<Payment> returnPaymentList() {
        return paymentList;
    }

    Hotel() {
        this.hotel_name = "Royal Scholar Inn";
        this.address = "Jalan Skudai, UTM, Skudai, Johor";
        this.desc = "Royal 5 Star Hotel";
        // this.roomList = new ArrayList<>();
        // this.customerList = new ArrayList<>();
    }

    public void displayHotelInfo() {
        System.out.printf("\n\n ----------Welcome to %s Hotel----------\n", hotel_name);
        System.out.println("Description: " + desc);
        System.out.println("Address: " + address);
    }

    public String checkRoomStatus(int index) {
        String roomStatus;
        if (roomList[index].getEmpty() == false) {
            roomStatus = "Unavailable";
        } else {
            roomStatus = "Available";
        }
        return roomStatus;
    }

    public String checkCheckin(int index) {
        String checkStatus;
        if (roomList[index].getCheckin() == false) {
            checkStatus = "Unchecked";
        } else {
            checkStatus = "Checked";
        }
        return checkStatus;
    }

    public String getRoomNo(int index) {
        String no = roomList[index].getRoomNo();
        return no;
    }

    public boolean checkRoomAvailability(int index) {
        if (roomList[index].getEmpty() == false) {
            return false;
        } else {
            return true;
        }
    }

    public void toggleRoomAvailability(int index) {
        if (roomList[index].roomEmpty == true)
            roomList[index].roomEmpty = false;
        else
            roomList[index].roomEmpty = true;
    }

    public void toggleCheckin(int index) {
        if (roomList[index].checkInStatus == false) {
            roomList[index].checkInStatus = true;
            System.out.println("Check-in status update successful!");
        } else
            roomList[index].checkInStatus = false;
    }

    public void displayRoom(int index) {
        System.out.println("Room No: " + roomList[index].getRoomNo());
        System.out.println("Room Type: " + roomList[index].getRoomType());
        System.out.println("Room Price: " + roomList[index].getRoomPrice());
        System.out.println("Room Floor: " + roomList[index].getFloor());
        System.out.println("Room Availabity: " + checkRoomStatus(index));
    }

    public void displayAllRoom() {
        String formatHeader = new String("%-3s%-10s%-10s%-10s%-10s%-15s%-10s\n");
        String formatContent = new String("%-3d%-10s%-10s%-10.2f%-10d%-15s%-10s\n");

        System.out.println("");
        System.out.printf(formatHeader, "No", "RoomNo", "Type", "Price(RM)", "Floor", "Availability", "Check-in");
        for (int i = 0; i < roomList.length; i++) {
            System.out.printf(formatContent, (i + 1), roomList[i].getRoomNo(), roomList[i].getRoomType(),
                    roomList[i].getRoomPrice(), roomList[i].getFloor(), checkRoomStatus(i), checkCheckin(i));
        }
        System.out.println(" ");
    }

}
