package market.customer;


/**
 * This class consists of a Customer object. Each Customer has its own unique ID.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class Customer {

	private final int customerid;
	private double timeInQueue;
	private double queueArrival;
	private boolean hasQueued = false;
	
	/**
	 * @param customerID
	 */
	protected Customer(int customerid) {
		this.customerid = customerid;
	}
	/**
	 * 
	 * @return ID.
	 */
	public int ID() {
		return customerid;
	}
	
	public double getTimeInQueue(double time) {
		// The customer is still in the queue if the counted time is still 0. Do not change this.
		if (this.hasQueued && this.timeInQueue == 0) {
			return time-this.queueArrival;
		}
		return this.timeInQueue;
	}
	
	/**
	 * Invoke this when a customer arrives at the queue.
	 * @param time
	 */
	public void arriveToQueue(double time) {
		this.queueArrival = time;
		this.hasQueued = true;
	}
	
	/**
	 * Invoke this when a customer has left the queue.
	 * @param time - The current time.
	 */
	public void leaveQueue(double time) {
		this.timeInQueue = this.hasQueued ? time-this.queueArrival : 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Customer) {
			throw new IllegalArgumentException();
		}
		return this.ID() == ((Customer)o).ID();
	}
	
	@Override
	public String toString() {
		return this.ID() + "";
	}
}
