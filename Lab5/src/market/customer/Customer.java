package market.customer;


/**
 * This class consists of a Customer object. Each Customer has its own unique ID.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class Customer {

	private final int customerid;
	double timeinqueue;
	double queueArrival;
	
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
	
	public double getTimeInQueue() {
		return this.timeinqueue;
	}
	
	/**
	 * Invoke this when a customer arrives at the queue.
	 * @param time
	 */
	public void arriveToQueue(double time) {
		this.queueArrival = time;
	}
	
	/**
	 * Invoke this when a customer has left the queue.
	 * @param time - The current time.
	 */
	public void leaveQueue(double time) {
		this.timeinqueue = time-this.queueArrival;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Customer) {
			throw new IllegalArgumentException();
		}
		return this.ID() == ((Customer)o).ID();
	}
}
