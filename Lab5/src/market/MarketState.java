package market;

import market.checkout.Checkout;
import market.checkout.CheckoutFactory;
import market.customer.Customer;
import market.customer.Customerfactory;
import simulator.State;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;
import simulator.stream.*;

public class MarketState extends State {
	private QUEUE<Customer> checkoutQueue;
	private CheckoutFactory checkouts;
	private ExponentialRandomStream customerArrivalTime;
	private UniformRandomStream checkoutTime;
	private UniformRandomStream untilQueueTime;
	private long seed;
	private double[] checkoutTimeBounds, untilQueueTimeBounds;
	private int maxCustomers, totalCustomers, missedCustomers;

	private int customersInside = 0;

	private boolean isOpen = false;

	/**
	 * Constructor for MarketState
	 * 
	 * @param maxCustomers
	 *            amount of customers that can be inside the store.
	 * @param arrivalTime
	 *            the lambda of time when customers arrive.
	 * @param checkoutTime
	 *            the lower and upper bound of the time it takes to pay and leave.
	 * @param pickingTime
	 *            the lower and upper bound of the time to pick the groceries'.
	 * @param numberOfCheckouts
	 *            the amount of checkouts the store has.
	 * @param seed
	 *            the seed all randomness is based of.
	 */

	public MarketState(int maxCustomers, double arrivalTime, double[] checkoutTime, double[] pickingTime,
			int numberOfCheckouts, long seed) {
		this.checkoutQueue = new FifoQueue<Customer>();
		this.customerArrivalTime = new ExponentialRandomStream(arrivalTime, seed);
		this.checkoutTime = new UniformRandomStream(pickingTime[0], pickingTime[1], seed);
		this.untilQueueTime = new UniformRandomStream(checkoutTime[0], checkoutTime[1], seed);
		this.checkouts = new CheckoutFactory(numberOfCheckouts);

		this.checkoutTimeBounds = checkoutTime;
		this.untilQueueTimeBounds = pickingTime;
		this.maxCustomers = maxCustomers;
		this.seed = seed;
	}

	public boolean marketIsFull() {
		return this.maxCustomers == this.customersInside;
	}

	public void addCustomer() {
		this.customersInside++;
		this.totalCustomers++;
	}

	public int getTotalCustomers() {
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

	/**
	 * Set the market to open.
	 */
	public void setOpen() {
		this.isOpen = true;
	}

	/**
	 * Set the market to closed.
	 */
	public void setClosed() {
		this.isOpen = false;
	}

	public boolean isOpen() {
		return this.isOpen;
	}

	/**
	 * Returns the time for when the next customer arrives.
	 * 
	 * @param time
	 *            - The current time.
	 * @return
	 */
	public double nextArrivalTime(double time) {
		return this.customerArrivalTime.next() + time;
	}

	/**
	 * Returns the time for when the next customer will arrive at the checkout
	 * queue.
	 * 
	 * @param time
	 *            - The current time.
	 * @return
	 */
	public double nextToQueueTime(double time) {
		return this.untilQueueTime.next() + time;
	}

	/**
	 * Return the time for when the next customer will be done at the checkout.
	 * 
	 * @param time
	 *            - The current time.
	 * @return
	 */
	public double nextCheckoutTime(double time) {
		return this.checkoutTime.next() + time;
	}

	/**
	 * See if the checkout queue is empty.
	 * 
	 * @return - <b>true</b> if it is empty.
	 * @throws NullPointerException
	 *             - All checkouts are used.
	 */
	public boolean checkoutQueueIsEmpty() {
		return this.checkoutQueue.isEmpty();
	}

	public boolean hasAvailableCheckout() {
		return !this.checkouts.isFull();
	}

	/**
	 * Request a checkout to use.
	 * 
	 * @param time
	 *            - The current time.
	 * @return
	 * @throws NullPointerException
	 */
	public Checkout requestCheckout(double time) throws NullPointerException {
		return this.checkouts.requestCheckout(time);
	}

	/**
	 * Return the seed for the random generators.
	 * 
	 * @return
	 */
	public long getSeed() {
		return this.seed;
	}

	@Override
	public String[] sendData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
