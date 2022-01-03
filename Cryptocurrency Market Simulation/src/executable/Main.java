package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.Market;
import elements.Trader;
/**
 * This class is runnable part of this simulation. Each case is handled seperately in that class.
 * Also input and output processes are handeled here. 
 * @author Ahmet Oguz Engin
 *
 */
public class Main{
	
	public static Random myRandom;
	/**
	 * This main method contains 3 parts such as input handling , making necessary operations and printing outputs.
	 * Input Handling: In this part we get necessarry inputs from args[0] and handle according to given description.
	 * Making necessary operations: According to given operation number we make different operations to simulate the market.
	 * Printing outputs: In this part we print outputs accordingly.    
	 * @param args file 0 for input and file 1 for output. 
	 */
	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		Scanner reader;
		try {
			reader = new Scanner(inputFile);
		}catch(FileNotFoundException e) {
			return;
		}
		PrintStream outstream;
		
		try {
			outstream = new PrintStream(outputFile);
		}catch(FileNotFoundException e) {
			reader.close();
			return;		
		}
		
		//Input Handling
		int randomSeed = reader.nextInt();
		myRandom = new Random(randomSeed);
		int transactionFee = reader.nextInt();
		Market market = new Market(transactionFee);
		int numOfUsers = reader.nextInt();
		int numOfQueries = reader.nextInt();
		int numOfInvalid = 0;
		int num;
		ArrayList<Trader> traders = new ArrayList<Trader>(); 
		for(int i = 0; i<numOfUsers;i++){
			double dollars = reader.nextDouble();
			double coins = reader.nextDouble();
			Trader trader = new Trader(dollars,coins);
			traders.add(trader);
		}
		for(int k = 0; k<numOfQueries; k++) {
			int operationNum = reader.nextInt();
			switch(operationNum) {
			case 10:
				int traderID = reader.nextInt();
				double price = reader.nextDouble();
				double amount = reader.nextDouble();
				num = traders.get(traderID).buy(amount, price, market);
				if(num == 0) {
					numOfInvalid++;
				}
				market.checkTransactions(traders);
				break;
				
			case 11:
				int traderIDs = reader.nextInt();
				double amounts = reader.nextDouble();
				if(market.getSellingOrders().isEmpty()) {
					numOfInvalid++;
					break;
				}
				num = traders.get(traderIDs).buy(amounts,market.getBuyingPrice(), market);
				if(num == 0) {
					numOfInvalid++;
				}
				market.checkTransactions(traders);
				break;
				
			case 20:
				int traderID1 = reader.nextInt();
				double price1 = reader.nextDouble();
				double amount1 = reader.nextDouble();
				num = traders.get(traderID1).sell(amount1, price1, market);
				if(num == 0) {
					numOfInvalid++;
				}
				market.checkTransactions(traders);
				break;
				
			case 21:
				int traderIDb = reader.nextInt();
				double amountb = reader.nextDouble();
				if(market.getBuyingOrders().isEmpty()) {
					numOfInvalid++;
					break;
				}
				num = traders.get(traderIDb).sell(amountb,market.getSellingPrice(), market);
				if(num == 0) {
					numOfInvalid++;
				}
				market.checkTransactions(traders);
				break;
				
			case 3:
				int traderID2 = reader.nextInt();
				double amount2 = reader.nextDouble();
				traders.get(traderID2).deposit(amount2);
				market.checkTransactions(traders);
				break;
				
			case 4:
				int traderID3 = reader.nextInt();
				double amount3 = reader.nextDouble();
				num = traders.get(traderID3).withdraw(amount3);
				if(num == 0) {
					numOfInvalid++;
				}
				market.checkTransactions(traders);
				break;
				
			case 5:
				int traderID4 = reader.nextInt();
				String output = traders.get(traderID4).getWalletStatus();
				outstream.println(output);
				break;
			case 777:
				for(Trader each: traders) {
					each.addPQoins(myRandom.nextDouble() * 10);
				}
				market.checkTransactions(traders);
				break;
				
			case 666:
				double priceo = reader.nextDouble();
				market.makeOpenMarketOperation(priceo, traders);
				market.checkTransactions(traders);
				break;
				
			case 500:
				outstream.println(market.getMarketSize());
				market.checkTransactions(traders);
				break;
				
			case 501:
				outstream.println("Number of successful transactions: " + market.getNumOfSuccessfulTransact());
				market.checkTransactions(traders);
				break;
				
			case 502:
				outstream.println("Number of invalid queries: " + numOfInvalid);
				market.checkTransactions(traders);
				break;
				
			case 505:
				String output2 = String.format("Current prices: %.5f %.5f %.5f", market.getSellingPrice(), market.getBuyingPrice(), market.getAveragePrice());
				outstream.println(output2);
				market.checkTransactions(traders);
				break;
			case 555:
				for(Trader each: traders) {
					outstream.println(each.getWalletStatus());
				}
				market.checkTransactions(traders);
				break;
				
			}
			
		}
		reader.close();
		outstream.close();
		
		
	}
	
	
}

