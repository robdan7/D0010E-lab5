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
	private DataPackage data;
	
	QueueEvent(Customer c, double time, DataPackage data) {
		super(c, time);
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
			eventQueue.addEvent(new CheckoutEvent(super.getCustomer(), c, t, data));
		} 
		
		// All checkouts are used. Go to the queue.
		else {
			this.data.addQueuedCustomer();
			super.getCustomer().arriveToQueue(super.getTime());
			this.data.getCheckoutQueue().add(super.getCustomer());
		}
	}

	@Override
	public String toString() {
		return "Betalkö";
	}
	
}
