package elements;
/**
 * This class is to create orders with the trader's ID, amount of order and price of order.
 * @author Ahmet Oguz Engin
 *
 */
abstract public class Order {
	/**
	 * amount of order
	 */
	double amount;
	/**
	 * price of order
	 */
	double price;
	/**
	 * the ID of trader that gives order
	 */
	int traderID;
	/**
	 * Constructor method to create orders with 3 paramaters such as traderID, amount and price.
	 * @param traderID the ID of trader
	 * @param amount the amount of order
	 * @param price the price of order
	 */
	public Order(int traderID, double amount, double price) {
		this.amount = amount;
		this.traderID = traderID;
		this.price = price;
	}
}

