package market.modifiers;

import market.MarketState;
import market.checkout.Checkout;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.QUEUE;

public class CheckoutEvent extends Event {
	private Customer customer;
	private Checkout checkout;
	private QUEUE<Customer> customerQueue;

	CheckoutEvent(Customer object, Checkout checkout, QUEUE<Customer> customerQueue, double d) {
		super(d);
		this.customer = object;
		this.checkout = checkout;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if ((state instanceof MarketState)) {
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
