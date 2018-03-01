package market;

public class Optimize {
	static int maxCustomers=5;
	static double[] pickingTime= {0.5f,0.8f}, checkoutTime= {0.2f,0.8f}; 
	static double arrivalTime=2;
	static int numberOfCheckouts=2;
	static long seed=System.currentTimeMillis();

	public static void main(String[] args) {

		MarketState market=new MarketState(maxCustomers,arrivalTime,checkoutTime,pickingTime,numberOfCheckouts,seed);
		

	}

}
