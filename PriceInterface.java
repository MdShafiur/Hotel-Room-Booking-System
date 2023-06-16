//interface 
public interface PriceInterface {
    final double weekend_discount = 0.88;
    final double cleaning_fee = 45.8;

    double calcPrice();
}
// interface, holidat, weekday price, attribute: discout
// coonnect to super class(HOtel)
// aggration - Room(super class) - single, double,..
// room - aggration to Customer - Composition- Payment,
// Admin displayC()