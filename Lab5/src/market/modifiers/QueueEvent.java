package market.modifiers;

import market.MarketState;
import market.checkout.Checkout;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;

public class QueueEvent extends Event {
	private Customer customer;
	private QUEUE<Customer> customerQueue;
	
	QueueEvent(Customer c,QUEUE<Customer> queue, double time) {
		super(time);
		this.customer = c;
		this.customerQueue = queue;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		// If the queue is empty and at least one checkout is closed: Go to checkout.
		if (((MarketState)state).checkoutQueueIsEmpty() && ((MarketState)state).hasAvailableCheckout()) {
			Checkout c = ((MarketState)state).requestCheckout(super.getTime());
			double t = ((MarketState)state).nextCheckoutTime(super.getTime());
			eventQueue.addEvent(new CheckoutEvent(this.customer, c, customerQueue, t));
		} 
		
		// All checkouts are used and the queue is not 
		else {
			this.customer.arriveToQueue(super.getTime());
			this.customerQueue.add(this.customer);
		}
	}
	
}
