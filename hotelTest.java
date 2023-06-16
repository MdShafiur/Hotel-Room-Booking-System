import java.util.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class hotelTest {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws ParseException, IOException {
        // hotel user: admin, customer
        // Hotel test = new Hotel();
        // test.displayAllRoom();

        Admin adminZhu = new Admin();
        ArrayList<Customer> customerList = new ArrayList<Customer>();

        // existed customer 1
        Customer customer1 = new Customer("yichen", "123456", "Zhu YiChen", "013423563", "20021836253");
        Date datein1 = new SimpleDateFormat("dd/MM/yyyy").parse("10/7/2022");
        Date dateout1 = new SimpleDateFormat("dd/MM/yyyy").parse("13/7/2022");
        // customer1.payRoom(2, datein1, dateout1);
        // add to list
        customerList.add(customer1);

        // make action
        int firstOption = 0;
        boolean firstStatus = false;
        // test option valid
        do {
            // clearScreen();
            clearScreen();
            firstOption = mainMenu();
            if ((firstOption > 4) || (firstOption < 1)) {
                System.out.println("\nWrong input data, please enter integer[1-4]");
            } else {
                firstStatus = true;
            }
            switch (firstOption) {
                case 1:
                    // admin login
                    clearScreen();
                    adminZhu.adminLoginValid();

                    firstStatus = false;
                    break;
                case 2:
                    // customer login
                    clearScreen();
                    boolean custStatus = false;
                    while (custStatus == false) {

                        System.out.print("<< Customer Login >>\n");
                        in.nextLine();
                        System.out.print("Enter customer Login name: ");
                        String custTempName = in.nextLine();
                        System.out.print("Enter customer Login password: ");
                        String custTempPass = in.nextLine();
                        int findIndex = customerNameSearch(customerList, custTempName, custTempPass);
                        if (findIndex > -1) {
                            if ((custTempPass.compareTo(customerList.get(findIndex).returnPass()) == 0)) {
                                System.out.println("Customer login successful!");
                                custStatus = true;
                                // display menu
                                customerList.get(findIndex).customerMenu();
                            } else {
                                System.out.println("Wrong login password!");
                                custStatus = false;
                            }
                        } else {
                            System.out.println("Wrong login name");
                            custStatus = false;
                        } // end ifelse
                    } // end while

                    firstStatus = false;
                    break;
                case 3:
                    // customr register
                    System.out.println("<< New Customer Register >>");
                    customerList.add(addCustomer());
                    firstStatus = false;
                    break;
                case 4:
                    // quit system

                    break;
            }// end switch

        } while (firstStatus == false);

    }

    public static int mainMenu() {
        System.out.println("---------------------------------------------");
        System.out.println("    welcome to royal hotel booking system");
        System.out.println("---------------------------------------------\n");
        System.out.println("Please choose one action to continue...");
        System.out.println("1. Admin login\n2. Customer login\n3. Customer register\n4. quit system");
        System.out.print("enter your option [1-4]: ");
        int mainOption = in.nextInt();
        return mainOption;
    }

    public static Customer addCustomer() {
        clearScreen();
        System.out.println("---------------------------------------------");
        System.out.println("            welcome new customer");
        System.out.println("---------------------------------------------");
        System.out.print("Set Full name: ");
        in.nextLine();
        String loginFullname = in.nextLine();
        System.out.print("Set Login name: ");
        String loginName = in.nextLine();
        System.out.print("Set Login password: ");
        String loginPassword = in.nextLine();
        System.out.print("Set contact no: ");
        String loginContact = in.nextLine();
        System.out.print("Set IC: ");
        String loginIC = in.nextLine();
        Customer newCustomer = new Customer(loginName, loginPassword, loginFullname, loginContact, loginIC);
        System.out.println("New Customer Register successfully!");
        return newCustomer;
    }

    public static int customerNameSearch(ArrayList<Customer> ca, String tempName, String tempPass) {
        for (int i = 0; i < ca.size(); i++) {
            if (tempName.compareTo(ca.get(i).returnName()) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pressToContinue() {
        System.out.print("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
