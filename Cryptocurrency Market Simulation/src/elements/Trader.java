package elements;
/**
 * This class is to create new trader accounts with their wallets.
 * Also this class enables to give buying orders and selling orders.
 * It's also possible to get information about current wallet status.
 * Also withdrawing and depositing money is possible by using proper methods.
 * @author Ahmet Oguz Engin
 *
 */
public class Trader {
	/**
	 * The id of trader.
	 */
	private int id;
	/**
	 * The wallet of trader.
	 */
	private Wallet wallet;
	/**
	 * Number of users (also helps to determine the id of trader)
	 */
	public static int numberOfUsers = 0;
	/**
	 * Constructor of the trader with 2 parameters such as dollars and coins. 
	 * @param dollars the dollars of trader
	 * @param coins the coins of trader
	 */
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars,coins);
		this.id = numberOfUsers;
		numberOfUsers++;
	}
	/**
	 * This method is to sell a certain amount of coin at a certain price.
	 * @param amount the amount of coin to sell
	 * @param price the price at which coins will be sold
	 * @param market the market in which traders make transactions
	 * @return 0 if invalid, else 1
	 */
	public int sell(double amount, double price, Market market) {
		if(price<0) return 0;
		if(amount <= wallet.getCoins()) {
			market.giveSellOrder(new SellingOrder(id, amount, price));
			wallet.setBlockedCoins(wallet.getBlockedCoins() + amount);
			wallet.setCoins(wallet.getCoins() - amount);
			return 1;
		}
		return 0;
	}
	/**
	 * This method is to buy a certain amount of coin at a certain price.
	 * @param amount the amount of coin to buy
	 * @param price the price at which coins will be bought
	 * @param market the market in which traders make transactions
	 * @return 0 if invalid, else 1
	 */
	public int buy(double amount, double price, Market market) {
		if(price<0) return 0;
		if(amount*price <= wallet.getDollars()) {
			market.giveBuyOrder(new BuyingOrder(id, amount, price));
			wallet.setBlockedDollars(wallet.getBlockedDollars() + price*amount);
			wallet.setDollars(wallet.getDollars() - price*amount);
			return 1;
		}
		return 0;
	}
	/**
	 * for trader to deposit money to their wallets 
	 * @param amount the amount of dollars to deposit
	 */
	public void deposit(double amount) {
		this.wallet.setDollars(this.wallet.getDollars() + amount);
	}
	/**
	 * for trader to withdraw money from their wallets
	 * @param amount the amount of dollars to withdraw
	 * @return 0 if invalid, else 1
	 */
	public int withdraw(double amount) {
		if(amount <= this.wallet.getDollars()) {
			this.wallet.setDollars(this.wallet.getDollars() - amount);
			return 1;
		}
		else return 0;
	}
	/**
	 * to add PQoins to traders' wallets
	 * @param amount the amount of PQoin to be added
	 */
	public void addPQoins(double amount){
		this.wallet.setCoins(this.wallet.getCoins() + amount);
	}
	/**
	 * to get the wallet information of traders
	 * @return the wallet information of traders
	 */
	public String getWalletStatus(){
		return String.format("Trader %d: %.5f$ %.5fPQ",id, this.wallet.getDollars() + this.wallet.getBlockedDollars(),this.wallet.getCoins() + this.wallet.getBlockedCoins());
	}
	/**
	 * Returns the id of trader
	 * @return the id of trader
	 */
	public int getId() {
		return id;
	}
	/**
	 * to change the amount of blocked dollars
	 * @param amount the amount of blocked dollars to be added
	 */
	public void changeBlockedDollars(double amount) {
		this.wallet.setBlockedDollars(this.wallet.getBlockedDollars() + amount);
	}
	/**
	 * to change the amount of dollars
	 * @param amount the amount of dollars to be added
	 */
	public void changeDollars(double amount) {
		this.wallet.setDollars(this.wallet.getDollars() + amount);
	}
	/**
	 * to change the amount of blocked dollars
	 * @param amount the amount of blocked dollars to be added
	 */
	public void changeBlockedPQoins(double amount) {
		this.wallet.setBlockedCoins(this.wallet.getBlockedCoins() + amount);
	}
	
}
