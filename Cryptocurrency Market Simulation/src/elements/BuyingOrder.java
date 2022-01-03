package elements;
/**
 * BuyingOrder class is to create orders to buy PQoins in exchange for dollars. 
 * It contains a constructor with 3 parameters which are the traderID, amount of PQoins and price.
 * It also has a method named compareTo to sort buyingOrders in a priority queue properly.
 * @author Ahmet Oguz Engin
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * Constructor method for BuyingOrder. It has three parameters that are traderID, amount of PQoins and price.
	 * @param traderID ID of the trader. 
	 * @param amount The amount of PQoins to be bought.
	 * @param price	the price at which trader is willing to buy PQoins. 
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	
	/**
	 * This method is to sort buyingOrders in a priority queue properly.
	 * @param e buying order to compare with 
	 * @return 1 and -1 according to the result of proper comparisons.
	 */
	@Override
	public int compareTo(BuyingOrder e) {
		if(this.price == e.price) {
			if(this.amount < e.amount) {
				return 1;
			}
			else if(this.amount > e.amount) {
				return -1;
			}
			else {
				return this.traderID - e.traderID;
			}
		}
		else if(this.price < e.price) {
			return 1;
		}
		return -1;
	}
	

}
