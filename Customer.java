import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.*; // Import the File class

public class Customer extends system_User {

    // private int customerID;
    private String phoneNo;
    private String IC;
    private Hotel hotel = new Hotel();
    private Payment payment;
    // private Transaction transactions = new Transaction();
    // private boolean login_status = false;
    // private Payment payment = new Payment();
    Scanner in = new Scanner(System.in);

    /*
     * Customer menu
     * 1. booking room
     * 2. Print transaction
     * 3. log out
     */
    Customer() {
        super("", "");
        this.phoneNo = "";
        this.IC = "";
    }

    Customer(String n, String pw, String fn, String phone, String ic) {
        super(n, pw);
        this.phoneNo = phone;
        this.fullname = fn;
        this.IC = ic;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setFullName(String fn) {
        this.fullname = fn;
    }

    public void setPass(String pw) {
        this.password = pw;
    }

    public void setPhone(String phone) {
        this.phoneNo = phone;
    }

    public void setIC(String ic) {
        this.IC = ic;
    }

    public String returnName() {
        return this.name;
    }

    public String returnPass() {
        return this.password;
    }

    public void customerMenu() throws ParseException, IOException {
        clearScreen();
        boolean custStatus = false;
        while (custStatus == false) {
            System.out.println("---------------------------------------------");
            System.out.println("    welcome to royal hotel booking system");
            System.out.println("---------------------------------------------\n");
            System.out.println("Please choose one action to continue...");
            System.out.println("1. Booking Room\n2. Print transaction\n3. Log out");
            System.out.print("enter your option [1-3]: ");
            int customerOption = in.nextInt(); // enter option
            if ((customerOption > 3) || (customerOption < 1)) {
                System.out.println("\nWrong input data, please enter integer[1-4]");
            } else {
                custStatus = true;
            }

            switch (customerOption) {

                case 1:
                    // 1 booking room
                    clearScreen();
                    bookRoom();
                    pressToContinue();
                    custStatus = false;
                    break;

                case 2:
                    // 2. print transaction
                    clearScreen();
                    displayInfo();
                    payment.displayDetails();
                    pressToContinue();
                    custStatus = false;
                    break;

                case 3:
                    // 3. logout
                    break;
            }// end switch
        } // end while
    }//

    public void payRoom(int index) throws ParseException, IOException {
        File paymentFile = new File("paymentInfo.txt"); // creat file
        if (paymentFile.createNewFile()) {
            System.out.println("File created succesfully");
        } else {
            System.out.println("File already exists.");
        }

        FileWriter writer = new FileWriter("paymentInfo.txt", true);
        String tempName = hotel.getRoomNo(index);
        writer.write(tempName + "\n");// write
        String tempCheckin = "11/7/2022";
        String tempCheckout = "11/7/2022";
        double tempPrice = Hotel.roomList[index].getRoomPrice();
        writer.write((double) tempPrice + "\n");// write
        System.out.print("Check in date: ");
        tempCheckin = in.nextLine();
        writer.write(tempCheckin + "\n");// write
        System.out.print("Check out date: ");
        tempCheckout = in.nextLine();
        writer.write(tempCheckout + "\n");// write
        Date datein = new SimpleDateFormat("dd/MM/yyyy").parse(tempCheckin);
        Date dateout = new SimpleDateFormat("dd/MM/yyyy").parse(tempCheckout);

        int tempDays = ((int) (dateout.getTime() - datein.getTime())) / 1000 / 60 / 60 / 24;
        writer.write((int) tempDays + "\n");// write
        writer.close();// close writer;
        // System.out.println(tempDays);
        // System.out.println("Days: " + tempDays / 1000 / 60 / 60 / 24);
        payment = new Payment(tempName, tempDays, tempPrice, datein, dateout);
        hotel.addPayment(payment);
        // System.out.println("Payment Amount: " + payment.calcPrice());
        payment.displayDetails();
        pressToContinue();
    }
    // 1 booking room
    // 1.2 choose room number
    // 1.3 choose days stay, 1.45 choose check in/out day, 1.6 room payment

    public void bookRoom() throws ParseException, IOException {
        int bookOption = 0;
        boolean optionStatus = false;
        clearScreen();
        System.out.println("<<Customer Booking Hotel Room>>");
        hotel.displayAllRoom();
        while (optionStatus == false) {
            System.out.print("Choose NO [1~12]: ");
            // in.nextLine();
            int inputRoomIndex = in.nextInt() - 1;
            if (hotel.checkRoomAvailability(inputRoomIndex) == false) {
                System.out.println("\nRoom is occupied!");
                pressToContinue();

            } else {
                hotel.displayRoom(inputRoomIndex);
                System.out.print("\nConfirm your booking? ['y'=yes, 'n'=no]: ");
                in.nextLine();
                String confirmRoom = in.nextLine();
                if (confirmRoom.toUpperCase().compareTo("Y") == 0) {
                    optionStatus = true;

                    hotel.toggleRoomAvailability(inputRoomIndex);// set room status to unavailable
                    payRoom(inputRoomIndex);
                    // payment
                } else if (confirmRoom.toUpperCase().compareTo("N") == 0) {
                    //
                } else {
                    System.out.println("\nWrong input data!");
                    pressToContinue();
                }
            }
        } // end while
    }
    // 2. print transaction

    // 3.logout?
    @Override
    public boolean Login(String id, String pw) {
        if ((id == userID) && (pw == password)) {
            login_status = true;
            System.out.printf("\n----------Welcome .s%----------\n", name);
            return login_status;
        } else {
            System.out.println("\n!!!!! Login information incorrect !!!!!\n");
            return login_status;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("\n\n ----------Customer Details----------");
        System.out.println("Customer: " + name);
        System.out.println("Customer Phone No: " + phoneNo);
        System.out.println("Customer IC: " + IC);
    }

}
