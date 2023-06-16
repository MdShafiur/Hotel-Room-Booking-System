import java.util.Calendar;
import java.util.Date;

public class Payment implements PriceInterface {

    protected String roomNO;
    protected int paymentID;
    protected double total_price = 0;
    protected double price_perday;
    protected int days;
    protected Date checkin_date;
    protected Date checkout_date;
    protected boolean weekend_status = false;
    protected Customer customer;

    // enum
    public enum paymentStatus {
        Pending,
        Approved,
        Refund
    }

    public paymentStatus pStatus = paymentStatus.Pending;

    Payment() {
        this.days = 0;
        this.price_perday = 0.0;
        this.checkin_date = new Date();
        this.checkout_date = new Date();
    }

    Payment(String room, int days, double priceday, Date checkin, Date checkout) {
        this.roomNO = room;
        this.days = days;
        this.price_perday = priceday;
        this.checkin_date = checkin;
        this.checkout_date = checkout;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setpriceday(int price) {
        this.price_perday = price;
    }

    public void setCheckin(Date checkin) {
        this.checkin_date = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout_date = checkout;
    }

    public void displayDetails() {
        // System.out.println("\n ----------Room Booking Paytment Details----------");
        // System.out.println(customer.fullname);
        System.out.println("Room No: " + this.roomNO);
        System.out.println("Payment Amount: " + this.calcPrice());
        System.out.println("Booking Days: " + this.days);
        System.out.println("Check in Date: " + this.checkin_date);
        System.out.println("Check out Date: " + this.checkout_date);
        System.out.println("Payment Status:" + pStatus);
        System.out.println("\n--------------------------------------------");
    }

    public static boolean isWeekend(final Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }

    public void customerPay(Customer c1) {
        this.customer = c1;
    }

    @Override
    public double calcPrice() {
        if (isWeekend(checkin_date) == true) {
            total_price = (price_perday * days * weekend_discount + cleaning_fee);
        } else {
            total_price = (price_perday * days + cleaning_fee);
        }
        return total_price;
    };
}