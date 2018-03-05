package market;

import java.util.Random;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class Optimize {
	private int maxCustomers=100;
	private double[] pickingTime= {0.45f,0.65f}, checkoutTime= {0.2f,0.3f}; 
	private double arrivalTime=50;
	private int numberOfCheckouts = 2;
	private double closingTime = 20;
	private long seed=42;
	private DataPackage data;
	
	public void runOnce() {
		data = new DataPackage(maxCustomers, numberOfCheckouts, arrivalTime, checkoutTime, pickingTime, closingTime, seed);
		new RunSim(data, 999, true);
	}
	
	/**
	 * find the most optimal amount of checkouts. This will run tests x amount of
	 * times with different seeds and varying checkouts, to get the most times the
	 * smallest fixed set of checkouts is optimal (the market is never full).
	 * 
	 * A large returned value might imply that more customers are entering than
	 * leaving. This is not caused by too few checkouts. It is probably caused by the
	 * maximum customers allowed in the market.
	 */
	public int runOptimized(int runs) {
		long[] seeds = new long[runs]; // Use one seed for every run.
		int[][] dataStorage = new int[runs][maxCustomers]; // Use this to store every run.
		Random r = new Random();
		r.setSeed(seed);
		for (int i = 0; i < runs; i++) {
			seeds[i] = r.nextLong(); // Set every seed.
		}
		// Run one time for every amount of checkouts. 1 to max numbers of customer.
		for(int currentRun = 0; currentRun < runs; currentRun++) {
			for (int checkouts = 1; checkouts <= maxCustomers; checkouts++) {
				data = new DataPackage(maxCustomers, checkouts, arrivalTime, checkoutTime, pickingTime, closingTime, seeds[currentRun]);
				new RunSim(data, 999, false);
				// Store the amount of missed customers.
				dataStorage[currentRun][checkouts-1] = data.getMissedCustomers();
			}
		}
		return compareData(dataStorage);
	}
	
	private int compareData(int[][] dataStorage) {
		int largestCheckoutsUsed = 1; // The most checkouts needed in a worst case scenario.
		
		// For every run: find the lowest amount of checkouts needed in a worst case scenario.
		for (int run = 0; run < dataStorage.length; run++) {
			int lostCustomers = dataStorage[run][0]; // The smallest recorded amount of lost customers.

			// For every amount of checkouts: Find the lowest value for missed customers and
			// document the checkouts used.
			for (int checkoutRun = 0; checkoutRun < dataStorage[run].length; checkoutRun++) {
				if (dataStorage[run][checkoutRun] < lostCustomers) {
					lostCustomers = dataStorage[run][checkoutRun];
					if (checkoutRun + 1 > largestCheckoutsUsed) {
						// This run used more checkouts but got a better result. Document it.
						largestCheckoutsUsed = checkoutRun+1;
					}
				}
			}
		}
		
		// The return value will be the best amount of checkouts to use in order to not lose any customers.
		return largestCheckoutsUsed;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <= 17; i++) {
			System.out.println("Test " + i+ " time: " + new Optimize().runOptimized(i));
		}
		new Optimize().runOnce();
	}
}
