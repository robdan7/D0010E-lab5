package market.modifiers;

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
public class CheckoutEvent extends Event {
	private Customer customer;
	private Checkout checkout;
	private QUEUE<Customer> customerQueue;

	/**
	 * Create a checkout event.
	 * @param c - The customer
	 * @param checkout - The checkout to use.
	 * @param customerQueue - The customer queue to take more customers from.
	 * @param time - The time a customer walks out.
	 */
	CheckoutEvent(Customer c, Checkout checkout, QUEUE<Customer> customerQueue, double time) {
		super(time);
		this.customer = c;
		this.checkout = checkout;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		this.checkout.setClosed(super.getTime());
		
		if (!this.customerQueue.isEmpty()) {
			double t = ((MarketState)state).nextCheckoutTime(super.getTime());
			CheckoutEvent e = new CheckoutEvent(this.customerQueue.next(), this.checkout, this.customerQueue, t);
			eventQueue.addEvent(null);
		} else {
			this.checkout.setClosed(super.getTime());
		}
		try {
			((MarketState)state).customerLeaves();
		} catch (Exception e) {
			e.printStackTrace(); // One customer is missing!
		}
	}

}
