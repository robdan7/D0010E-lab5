package market;

import market.customer.Customer;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;

/**
 * This class is a container for data passed between classes in the market program.
 * @author Robin
 *
 */
public class DataPackage {
	private final long seed;
	private final int maxCustomers;
	private int totalCustomers, missedCustomers;
	private final double arrivalTime, closingTime;
	private final double[] checkoutTime, pickingTime;
	QUEUE<Customer> checkoutQueue;

	private int customersInside = 0, checkouts;
	
	/**
	 * Constructor for data package.
	 * 
	 * @param maxCustomers
	 *            amount of customers that can be inside the store.
	 * @param checkouts
	 *            the amount of checkouts the store has.
	 * @param arrivalTime
	 *            the lambda of time when customers arrive.
	 * @param checkoutTime
	 *            the lower and upper bound of the time it takes to pay and leave.
	 * @param pickingTime
	 *            the lower and upper bound of the time to pick the groceries'.
	 * @param closingTime - Closing time for the market.
	 * @param seed
	 *            the seed all randomness is based of.
	 */
	public DataPackage(int maxCustomers, int checkouts, double arrivalTime, double[] checkoutTime, double[] pickingTime,
			double closingTime, long seed) {
		this.maxCustomers = maxCustomers;
		this.seed = seed;
		this.checkouts = checkouts;	
		this.checkoutTime = checkoutTime;
		this.pickingTime = pickingTime;
		this.arrivalTime = arrivalTime;
		this.closingTime = closingTime;
		this.checkoutQueue = new FifoQueue<Customer>();
	}
	
	public QUEUE<Customer> getCheckoutQueue() {
		return this.checkoutQueue;
	}
	
	double getArrivalTime() {
		return this.arrivalTime;
	}
	
	double[] getCheckoutTime() {
		return this.checkoutTime;
	}
	
	double[] getPickingTime() {
		return this.pickingTime;
	}
	
	double getClosingTime() {
		return this.closingTime;
	}
	
	int getCheckoutAmount() {
		return this.checkouts;
	}


	/**
	 * Return the seed for the random generators.
	 * 
	 * @return
	 */
	long getSeed() {
		return seed;
	}

	int getMaxCustomers() {
		return maxCustomers;
	}

	int getMissedCustomers() {
		return missedCustomers;
	}


	public void addMissedCustomers() {
		this.missedCustomers ++;
	}

	int getCustomersInside() {
		return customersInside;
	}

	public boolean marketIsFull() {
		return this.maxCustomers == this.customersInside;
	}

	public void addCustomer() {
		this.customersInside++;
		this.totalCustomers++;
	}

	int getTotalCustomers() {
		return this.totalCustomers;
	}

	public void addMissedCustomer() {
		this.missedCustomers++;
	}

	public void customerLeaves() throws Exception {
		if (this.customersInside <= 0) {
			throw new Exception("One customer is missing!");
		}
		this.customersInside--;
	}
}
