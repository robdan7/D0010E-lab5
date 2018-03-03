package market.modifiers;

import market.DataPackage;
import market.MarketState;
import market.checkout.Checkout;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.QUEUE;

/**
 * This class represents a customer checkout. A checkout event is active as long
 * as there are more customers in the queue. The chekout will close if the queue is empty.
 * 
 * @author Robin
 *
 */
public class CheckoutEvent extends MarketEvent {
	private Checkout checkout;
	private DataPackage data;

	/**
	 * Create a checkout event.
	 * @param c - The customer
	 * @param checkout - The checkout to use.
	 * @param customerQueue - The customer queue to take more customers from.
	 * @param time - The time a customer walks out.
	 */
	CheckoutEvent(Customer c, Checkout checkout, double time, DataPackage data) {
		super(c, time);
		this.checkout = checkout;
		this.data = data;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).notifyFromEvent(this);
		
		
		this.data.addQueueTime(super.getCustomer().getTimeInQueue(super.getTime()));
		
		if (!this.data.getCheckoutQueue().isEmpty()) {
			double t = ((MarketState)state).nextCheckoutTime(super.getTime());
			
			// Take next customer from the queue.
			Customer nextCustomer = this.data.getCheckoutQueue().next();
			// Make sure the customer queue timer stops.
			nextCustomer.leaveQueue(super.getTime());
			
			// Create next event for the next customer.
			CheckoutEvent e = new CheckoutEvent(nextCustomer, this.checkout, t, data);
			eventQueue.addEvent(e);
		} else {
			this.checkout.setClosed(super.getTime());
		}
		try {
			this.data.customerLeaves(super.getCustomer(), super.getTime());
		} catch (Exception e) {
			e.printStackTrace(); // One customer is missing!
		}
	}

	@Override
	public String toString() {
		return "Betalning";
	}

}
