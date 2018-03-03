package market;

import market.checkout.Checkout;
import market.checkout.CheckoutFactory;
import market.customer.Customer;
import market.customer.Customerfactory;
import market.modifiers.MarketEvent;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;
import simulator.stream.*;

public class MarketState extends State {
	private QUEUE<Customer> checkoutQueue;
	private CheckoutFactory checkouts;
	private double closedCheckoutTime = 0;
	private ExponentialRandomStream customerArrivalTime;
	private UniformRandomStream checkoutTime;
	private UniformRandomStream untilQueueTime;
	private MarketEvent latestEvent = null;
	private DataPackage data;
	

	private boolean isOpen = false;

	public MarketState(DataPackage data) {
		this.data = data;
		this.checkoutQueue = new FifoQueue<Customer>();
		this.customerArrivalTime = new ExponentialRandomStream(data.getArrivalTime(), data.getSeed());
		this.checkoutTime = new UniformRandomStream(data.getCheckoutTime()[0], data.getCheckoutTime()[1], data.getSeed());
		this.untilQueueTime = new UniformRandomStream(data.getPickingTime()[0], data.getPickingTime()[1], data.getSeed());
		this.checkouts = new CheckoutFactory(data.getCheckoutAmount());
	}
	
	DataPackage getData() {
		return this.data;
	}
	/**
	 * 
	 * @param event - The event.
	 */
	public void notifyFromEvent(MarketEvent event) {
		this.latestEvent = event;
		super.setChanged();
		super.notifyObservers();
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
	
	double getClosedCheckoutTime() {
		if (this.getFlag()) {
			this.closedCheckoutTime = this.checkouts.getTimeClosed(this.latestEvent.getTime());
		}
		return this.closedCheckoutTime;
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

	@Override
	public String toString() {
		if (this.latestEvent == null) {
			return "";
		} else if (this.latestEvent.getCustomer() == null) {
			return String.format("%.2f", this.latestEvent.getTime()) + "\t" + 
					this.latestEvent.toString();
		}
		return String.format("%.2f", this.latestEvent.getTime()) + "\t" + 
		this.latestEvent.toString() + "   \t" + 
		this.latestEvent.getCustomer().toString() + "\t" + 
		(this.isOpen ? "Ö":"S") + "\t" + 
		this.checkouts.getClosedcheckouts() + "\t" +
		String.format("%.2f",  this.getClosedCheckoutTime())+ "\t" +
		this.data.getCustomersInside() + "\t"+
		this.data.getTotalCustomers() + "\t" +
		this.data.getMissedCustomers() + "\t"+
		this.data.getTotalQueuedCustomers() + "\t" +
		String.format("%.2f", this.data.getTotalQueueTime(this.latestEvent.getTime())) +"\t"+
		this.data.getCustomersInQueue() + "\t" +
		this.data.getCheckoutQueue().toString();
	}
}
