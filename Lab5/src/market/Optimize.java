package market;

public class Optimize {
	public static void main(String[] args) {
		
		// Time in hrs.
		int maxCustomers=5;
		double[] pickingTime= {0.5f,1f}, checkoutTime= {2f,3f}; 
		double arrivalTime=1;
		int numberOfCheckouts=2;
		double closingTime = 10;
		long seed=1234;
		
		DataPackage data = new DataPackage(maxCustomers, numberOfCheckouts, arrivalTime, checkoutTime, pickingTime, closingTime, seed);
		
		new RunSim(data, 999);
	}

}
