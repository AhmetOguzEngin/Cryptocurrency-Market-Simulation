package elements;
/**
 * This class is to create selling orders with the trader's ID, amount of order and price of order.
 * @author ahmet
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	/**
	 * Constructor method to create selling orders with 3 paramaters such as traderID, amount and price.
	 * @param traderID the ID of trader
	 * @param amount the amount of order
	 * @param price the price of order
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		
	}
	/**
	 * This method is to compare selling orders properly to decide which comes first in a priority queue.
	 * @param e selling order to compare with
	 * @return 1 or -1 according to the result of necessary comparisons.
	 */
	@Override
	public int compareTo(SellingOrder e) {
		if(this.price == e.price) {
			if(this.amount < e.amount) {
				return 1;
			}
			else if(this.amount>e.amount) {
				return -1;
			}
			else {
				return this.traderID - e.traderID;
			}
		}
		else if(this.price > e.price) {
			return 1;
		}
		return -1;
		
		
	}
	
	
}
