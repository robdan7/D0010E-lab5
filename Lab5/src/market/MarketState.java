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
	private int seed;
	private int maxCustomers;
	
	private int customersInside = 0;
	
	private boolean isOpen = false;
	
	public MarketState(int maxCustomers, int arrivalTime, int checkoutTime, int pickingTime, int seed) {
		this.checkoutQueue = new FifoQueue<Customer>();
		this.customerArrivalTime = new ExponentialRandomStream(arrivalTime, seed);
		this.checkoutTime = new UniformRandomStream(pickingTime, seed);
		this.untilQueueTime = new UniformRandomStream(checkoutTime, seed);
		this.checkouts = new CheckoutFactory(seed);
		
		this.maxCustomers = maxCustomers;
		this.seed = seed;
	}
	
	public boolean marketIsFull() {
		return this.maxCustomers == this.customersInside;
	}
	
	public void addCustomer() {
		this.customersInside++;
	}
	
	public void customerLeaves() throws Exception{
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
	 * @param time - The current time.
	 * @return
	 */
	public double nextArrivalTime(double time) {
		return this.customerArrivalTime.next() + time;
	}
	
	/**
	 * Returns the time for when the next customer will arrive at the checkout queue.
	 * @param time - The current time.
	 * @return
	 */
	public double nextToQueueTime(double time) {
		return this.untilQueueTime.next() + time;
	}
	
	/**
	 * Return the time for when the next customer will be done at the checkout.
	 * @param time - The current time.
	 * @return
	 */
	public double nextCheckoutTime(double time) {
		return this.checkoutTime.next() + time;
	}
	
	/**
	 * See if the checkout queue is empty.
	 * @return - <b>true</b> if it is empty.
	 * @throws NullPointerException - All checkouts are used.
	 */
	public boolean checkoutQueueIsEmpty() {
		return this.checkoutQueue.isEmpty();
	}
	
	public boolean hasAvailableCheckout() {
		return !this.checkouts.isFull();
	}
	
	/**
	 * Request a checkout to use.
	 * @param time - The current time.
	 * @return
	 * @throws NullPointerException
	 */
	public Checkout requestCheckout(double time) throws NullPointerException{
		return this.checkouts.requestCheckout(time);
	}
	
	/**
	 * Return the seed for the random generators.
	 * @return
	 */
	public int getSeed() {
		return this.seed;
	}
}
