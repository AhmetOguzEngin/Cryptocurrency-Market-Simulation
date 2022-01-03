package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * Market class is the most important part of this simulation. 
 * It has some functionalites to make open market operations to increase and decrease the number of PQoins available in the market.
 * It also allows to make trades if it's possible.
 * It also provides information about selling price, buying price, average price and market fee.
 * 
 * @author Ahmet Oguz Engin
 *
 */
public class Market {
	/**
	 * The list which contains all selling orders.
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * The list which contains all buying orders.
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * The list of transactions that took place.
	 */
	private ArrayList<Transaction> transactions;
	/**
	 * The fee of market.
	 */
	private int fee;
	/**
	 * Constructor method for market class which has one parameter that is fee.
	 * @param fee The charge that market takes from seller for each transaction.
	 */
	public Market(int fee) {
		this.fee = fee;
		transactions = new ArrayList<Transaction>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		sellingOrders = new PriorityQueue<SellingOrder>(); 
	}
	/**
	 * This method is to add valid selling orders to the queue.
	 * @param order Selling order
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	/**
	 * This method is to add valid buying orders to the queue.
	 * @param order Buying order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
		
	}
	/**
	 * This method is to make open market operations in order to make arrangement on the market price and amount of PQoins.
	 * @param price price at which transactions will take place
	 * @param traders the list of traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		if(!sellingOrders.isEmpty()) {
			while(price >= sellingOrders.peek().price) {
				buyingOrders.add(new BuyingOrder(0, sellingOrders.peek().amount, sellingOrders.peek().price));
				checkTransactions(traders);
				if(sellingOrders.isEmpty()) break;
			}
		}
		if(!buyingOrders.isEmpty()) {
			while(price <= buyingOrders.peek().price ) {
				sellingOrders.add(new SellingOrder(0, buyingOrders.peek().amount, buyingOrders.peek().price));
				checkTransactions(traders);
				if(buyingOrders.isEmpty()) break;
			}
		}
	}
	/**
	 * This method is to check if there is any possible transaction.
	 * After valid transactions it makes necessary changes on both buyers' and sellers' wallets.
	 * Also keeps the recording of transactions. 
	 * @param traders the list of traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		if(!sellingOrders.isEmpty() && !buyingOrders.isEmpty()) {
			while(sellingOrders.peek().price <= buyingOrders.peek().price) {
				SellingOrder sellingOrder = sellingOrders.poll();
				BuyingOrder buyingOrder = buyingOrders.poll();
				double amountPQoin = Math.min(sellingOrder.amount, buyingOrder.amount);
				double priceOfTransaction = sellingOrder.price;
				//for buyers
				this.transactions.add(new Transaction(buyingOrder, sellingOrder));
				
				traders.get(buyingOrder.traderID).addPQoins(amountPQoin);
				traders.get(buyingOrder.traderID).changeBlockedDollars(-1*(amountPQoin * buyingOrder.price));
				traders.get(buyingOrder.traderID).changeDollars((buyingOrder.price - priceOfTransaction) * amountPQoin);
				buyingOrder.amount -= amountPQoin;
				
				//for sellers
				traders.get(sellingOrder.traderID).changeBlockedPQoins(-1*amountPQoin);
				traders.get(sellingOrder.traderID).changeDollars(amountPQoin*priceOfTransaction*(1-((double)getFee()/1000)));
				sellingOrder.amount -= amountPQoin;
				
				if(sellingOrder.amount > buyingOrder.amount) {
					sellingOrders.add(new SellingOrder(sellingOrder.traderID, sellingOrder.amount, sellingOrder.price));
				}
				else if(buyingOrder.amount > sellingOrder.amount) {
					buyingOrders.add(new BuyingOrder(buyingOrder.traderID, buyingOrder.amount, buyingOrder.price));
				}
				if(sellingOrders.isEmpty() || buyingOrders.isEmpty()) break;
			}
		}
	}
	/**
	 * This method provides information about market size.
	 * @return Market size in proper form.
	 */
	public String getMarketSize() {
		double totalDollars = 0;
		double totalCoins = 0;
		for(SellingOrder each: sellingOrders) {
			totalCoins += each.amount;
		}
		for(BuyingOrder order: buyingOrders) {
			totalDollars += order.amount*order.price;
		}
		return String.format("Current market size: %.5f %.5f", totalDollars, totalCoins); 
	}
	/**
	 * Returns the market fee.
	 * @return the market fee
	 */
	public int getFee() {
		return fee;
	}
	/**
	 * To change the market fee.
	 * @param fee the new market fee
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}
	/**
	 * Returns the selling price.
	 * @return the selling price.
	 */
	public double getSellingPrice() {
		if(buyingOrders.isEmpty()) return 0;
		return buyingOrders.peek().price;
	}
	/**
	 * Returns the buying price
	 * @return buying price
	 */
	public double getBuyingPrice() {
		if(sellingOrders.isEmpty()) return 0;
		return sellingOrders.peek().price;
	}
	/**
	 * Returns the average price.
	 * @return the average price
	 */
	public double getAveragePrice() {
		if(sellingOrders.isEmpty() && buyingOrders.isEmpty()) return 0;
		else if(buyingOrders.isEmpty()) return sellingOrders.peek().price;
		else if(sellingOrders.isEmpty()) return buyingOrders.peek().price;
		return (sellingOrders.peek().price + buyingOrders.peek().price) / 2;
	}
	/**
	 * Returns the number of successful transactions
	 * @return the number of successful transactions
	 */
	public int getNumOfSuccessfulTransact() {
		return transactions.size();
	}
	/**
	 * Returns the list of selling orders 
	 * @return the list of selling orders
	 */
	public PriorityQueue<SellingOrder> getSellingOrders(){
		return sellingOrders;
	}
	/**
	 * Returns the list of buying orders
	 * @return the list of buying orders
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders(){
		return buyingOrders;
	}
}
