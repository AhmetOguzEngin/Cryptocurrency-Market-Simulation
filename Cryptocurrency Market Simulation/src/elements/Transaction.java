package elements;

/**
 * This class is a data class which aims to keep records of transactions took place.
 * @author Ahmet Oguz Engin
 *
 */
public class Transaction {
	/**
	 * selin order took place
	 */
	private SellingOrder sellingOrder;
	/**
	 * buying order took place
	 */
	private BuyingOrder buyingOrder;
	/**
	 * Constructor method to keep information of transaction.
	 * @param buyingOrder buying order
	 * @param sellingOrder selling order
	 */
	public Transaction(BuyingOrder buyingOrder, SellingOrder sellingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
	/**
	 * Returns the selling order
	 * @return the selling order
	 */
	public SellingOrder getSellingOrder() {
		return sellingOrder;
	}
	/**
	 * Returns the buying order
	 * @return the buying order
	 */
	public BuyingOrder getBuyingOrder() {
		return buyingOrder;
	}
}
