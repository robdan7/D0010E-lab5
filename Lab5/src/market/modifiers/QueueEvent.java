package market.modifiers;

import market.DataPackage;
import market.MarketState;
import market.checkout.Checkout;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;

/**
 * This class represents a customer walking to the checkout queue. The customer
 * is either put in the queue or at a chekout if it is empty and at least one chekout is not used.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class QueueEvent extends MarketEvent {
	private Customer customer;
	private DataPackage data;
	
	QueueEvent(Customer c, double time, DataPackage data) {
		super(c, time);
		this.customer = c;
		this.data = data;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).notifyFromEvent(this);
		// If the queue is empty and at least one checkout is closed: Go to checkout.
		if (((MarketState)state).checkoutQueueIsEmpty() && ((MarketState)state).hasAvailableCheckout()) {
			Checkout c = ((MarketState)state).requestCheckout(super.getTime());
			double t = ((MarketState)state).nextCheckoutTime(super.getTime());
			eventQueue.addEvent(new CheckoutEvent(this.customer, c, t, data));
		} 
		
		// All checkouts are used and the queue is not 
		else {
			this.customer.arriveToQueue(super.getTime());
			this.data.getCheckoutQueue().add(this.customer);
		}
	}

	@Override
	public String toString() {
		return "Betalkö";
	}
	
}
