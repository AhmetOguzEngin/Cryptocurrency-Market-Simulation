package elements;
/**
 * This class is to keep information of trader's coins and dollars.
 * Also it's possible to make change on these amounts by using proper methods.
 * @author Ahmet Oguz Engin
 *
 */
public class Wallet {
	/**
	 * amount of dollars
	 */
	private double dollars;
	/*
	 * amount of coins
	 */
	private double coins;
	/**
	 * amount of blocked dollars
	 */
	private double blockedDollars;
	/**
	 * amount of blocked coins
	 */
	private double blockedCoins;
	/**
	 * Constructor method for wallet with two parameters such as dollars and coins.
	 * @param dollars the amount of dollars
	 * @param coins the amount of coins
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		this.blockedCoins = 0;
		this.blockedDollars = 0;
	}
	/**
	 * To change the amount of blocked dollars
	 * @param blockedDollars the amount of blocked dollars
	 */
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}
	/**
	 * Returns the amount of blocked dollars
	 * @return the amount of blocked dollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * Retruns the amount of dollars
	 * @return the amount of dollars
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 * To change the amount of dollars 
	 * @param dollars the amount of dollars
	 */
	public void setDollars(double dollars) {
		this.dollars = dollars;
	}
	/**
	 * Retruns the amount of coins
	 * @return the amount of coins
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 * To change the amount of coins
	 * @param coins the amount of coins
	 */
	public void setCoins(double coins) {
		this.coins = coins;
	}
	/**
	 * Returns the amount of blocked coins
	 * @return the amount of blocked coins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	/**
	 * To change the amount of blocked coins
	 * @param blockedCoins the amount of blocked coins
	 */
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
}
