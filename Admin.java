import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends system_User {

    private String email;
    // private String adminID;
    private Hotel hotel = new Hotel();// txt, static array
    private ArrayList<Room> roomList;
    private ArrayList<Payment> paymentList = hotel.returnPaymentList();
    Scanner in = new Scanner(System.in);

    // private boolean login_status = false;

    /*
     * Admin menu have
     * 1. approve customer's payment(approve, refine)
     * 2. approve checkin
     * 3. approve checkout
     * 4. view hotel information -(room info, customer info)
     * 5. exit
     */

    Admin() {
        super("zhu", "password");
        this.userID = "A001";
        this.email = "admin@hotel.com";

    }

    public void addPayment(Payment p) {
        paymentList.add(p);
    }

    // check login and provide menu once login successful
    public void adminLoginValid() throws FileNotFoundException, ParseException {
        boolean adminStatus = false;
        System.out.println("<< Admin Login >>");

        while (adminStatus == false) {
            System.out.print("\nEnter Admin Login name: ");
            String adminTempName = in.nextLine();
            System.out.print("Enter Admin Login password: ");
            String adminTempPass = in.nextLine();

            if ((adminTempName.compareTo(this.name) == 0) && (adminTempPass.compareTo(this.password) == 0)) {
                System.out.println("Admin login successful!");
                adminStatus = true;
            } else {
                System.out.println("Wrong login name/password!");

            }
        } // end while

        int adminMenuOption = 0;
        boolean adminMenuStatus = false; // valid option or not
        while ((adminStatus == true) && (adminMenuStatus == false)) {
            clearScreen();
            adminMenuOption = displayMenu();
            if ((adminMenuOption > 5) || (adminMenuOption < 1)) {
                System.out.println("Wrong input data, please enter integer[1-5]");
            } else {
                adminMenuStatus = true;
            }

            switch (adminMenuOption) {
                case 1:
                    clearScreen();
                    // approve customer's payment
                    displayPaymentList();
                    adminMenuStatus = false;
                    break;

                case 2:
                    // approve check in
                    clearScreen();

                    hotel.displayAllRoom();
                    System.out.print("<<Change the check in status>>\nChoose the No: ");
                    int num = in.nextInt();
                    hotel.toggleCheckin(num - 1);
                    adminMenuStatus = false;
                    pressToContinue();
                    break;

                case 3:
                    clearScreen();
                    // display room info
                    hotel.displayHotelInfo();
                    hotel.displayAllRoom();

                    pressToContinue();
                    adminMenuStatus = false;
                    break;
                case 4:
                    // admin logout
                    System.out.println("Thank you for using this system");
                    break;
            }
        }
    }

    // display the list of cusomer(payment)
    public void displayPaymentList() throws FileNotFoundException, ParseException {
        File file = new File("paymentInfo.txt");
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String tempNum = reader.nextLine();
            String tempPriceString = reader.nextLine();
            double tempPrice = Double.parseDouble(tempPriceString);
            String tempCheckin = reader.nextLine();
            Date datein = new SimpleDateFormat("dd/MM/yyyy").parse(tempCheckin);
            String tempCheckout = reader.nextLine();
            Date dateout = new SimpleDateFormat("dd/MM/yyyy").parse(tempCheckout);
            String tempDaysString = reader.nextLine();
            int tempDays = Integer.parseInt(tempDaysString);
            Payment payment = new Payment(tempNum, tempDays, tempPrice, datein, dateout);
            paymentList.add(payment);
        }
        reader.close();
        // this.paymentList = hotel.paymentList;
        if (paymentList.size() == 0) {
            System.out.println("Payment list is empty!");
            pressToContinue();
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("             Pending payment list");
            System.out.println("---------------------------------------------\n");
            int i;
            // int[] numList = new int[paymentList.size()];
            for (i = 0; i < paymentList.size(); i++) {
                if (paymentList.get(i).pStatus == Payment.paymentStatus.Pending) {
                    System.out.println("Pending Payment No: " + (i + 1));
                    paymentList.get(i).displayDetails();
                }
            } // end for

            System.out.print("Choose the No of payment to Approve/Reject\n");
            System.out.print("No of payment: ");
            int chooseNo = in.nextInt();
            approvePayment(chooseNo - 1);
        }
    }

    // 1
    public void approvePayment(int index) {
        boolean tempInput = false;
        while (tempInput == false) {
            System.out.print("Choose to approve the payment ['y'=Approved,'n'=Rejected]: ");
            in.nextLine();
            String temp = in.nextLine();
            try {
                if (temp.toUpperCase().compareTo("Y") == 0) {
                    paymentList.get(index).pStatus = Payment.paymentStatus.Approved;
                    System.out.println("Approved!");
                    pressToContinue();
                    tempInput = true;

                } else if (temp.toUpperCase().compareTo("N") == 0) {
                    paymentList.get(index).pStatus = Payment.paymentStatus.Refund;
                    System.out.println("Rejected!");
                    pressToContinue();
                    tempInput = true;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }

        }
    }

    public void approveCheckin(int index) {
        boolean tempInput = false;
        while (tempInput == false) {
            System.out.print("Approve the Checkin? ['y'=Approved,'n'=Leave]: ");
            in.nextLine();
            String temp = in.nextLine();
            try {
                if (temp.toUpperCase().compareTo("Y") == 0) {
                    hotel.toggleCheckin(index);
                    System.out.println("Approved!");
                    pressToContinue();
                    tempInput = true;

                } else {
                    // paymentList.get(index).pStatus = Payment.paymentStatus.Refund;
                    // System.out.println("Rejected!");
                    pressToContinue();
                    tempInput = true;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }

        }
    }

    public void displayRoomList() {
        System.out.println("Display the Pending payment list");
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).checkInStatus == false) {
                roomList.get(i).getRoomNo();
            }
        }
    }

    // 2
    public void checkCheckin() {
        System.out.println("enter the customerID want to condirm: ");
        int tempID = in.nextInt();

        System.out.println("Confirm the Checkin? ['y'= Yes,'n'= No]: ");
        String temp = in.nextLine();
        if (temp.toUpperCase() == "Y") {
            roomList.get(tempID).checkInStatus = true;

        }
    }

    // display adminmenu
    public int displayMenu() {
        System.out.println("---------------------------------------------");
        System.out.println("                Admin Menu");
        System.out.println("---------------------------------------------\n");
        System.out.println("Please choose one action to continue...");
        System.out.println(
                "1. approve customer's payment\n2. approve checkin\n3. view hotel information\n4. Logout");
        System.out.print("enter your option [1-5]: ");
        int mainOption = in.nextInt();
        return mainOption;
    }

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
        System.out.println("\n\n ----------Admin Details----------");
        System.out.println("Admin email: " + email);

    }

    public static void pressToContinue() {
        System.out.print("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
