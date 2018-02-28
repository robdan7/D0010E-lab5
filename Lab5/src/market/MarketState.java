package market;

import simulator.State;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;
import simulator.stream.*;

public class MarketState extends State {
	private QUEUE checkoutQueue;
	private ExponentialRandomStream customerArrivalTime;
	private UniformRandomStream checkoutTime;
	private UniformRandomStream untilQueueTime;
	
	public MarketState(int arrivalTime, int checkoutTime, int pickingTime, int seed) {
		this.checkoutQueue = new FifoQueue();
		this.customerArrivalTime = new ExponentialRandomStream(arrivalTime, seed);
		this.checkoutTime = new UniformRandomStream(pickingTime, seed);
		this.untilQueueTime = new UniformRandomStream(checkoutTime, seed);
	}
	
	/**
	 * Returns delta time for when the next customer arrives.
	 * @return
	 */
	public double nextCustomerTime() {
		return this.customerArrivalTime.next();
	}
	
	/**
	 * Returns delta time for when the next customer will arrive at the checkout queue.
	 * @return
	 */
	public double nextToQueueTime() {
		return this.untilQueueTime.next();
	}
	
	/**
	 * Return the delta time for when the next customer will be done at the checkout.
	 * @return
	 */
	public double nextCheckoutTime() {
		return this.checkoutTime.next();
	}
	
	/**
	 * See if the checkout queue is empty.
	 * @return - <b>true</b> if it is empty.
	 */
	public boolean checkoutIsEmpty() {
		return this.checkoutQueue.isEmpty();
	}
}
