package market.modifiers;

import market.MarketState;
import market.customer.Customer;
import market.customer.Customerfactory;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;

public class ArrivalEvent extends Event {
	private Customerfactory factory;
	private QUEUE<Customer> customerQueue;

	/**
	 * Create the first arrivalEvent for new customers.
	 * 
	 * @param time
	 *            - The time a customer arrives.
	 */
	ArrivalEvent(double time) {
		this(new Customerfactory(), new FifoQueue<Customer>(), time);
	}

	/**
	 * Create an event for new customers.
	 * 
	 * @param factory - The customer factory used by all previous arrivals.
	 * @param checkoutQueue - The common queue for future checkouts.
	 * @param time - The arrival time.
	 */
	private ArrivalEvent(Customerfactory factory, QUEUE<Customer> checkoutQueue, double time) {
		super(time);
		this.factory = factory;
		this.customerQueue = checkoutQueue;
	}

	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		eventQueue.addEvent(new QueueEvent(this.factory.newcustomer(), this.customerQueue,
				((MarketState) state).nextToQueueTime(super.getTime())));
		if (((MarketState) state).isOpen()) {
			eventQueue.addEvent(new ArrivalEvent(this.factory, this.customerQueue,
					((MarketState) state).nextArrivalTime(super.getTime())));
			if (!((MarketState)state).marketIsFull()) {
				((MarketState) state).addCustomer();
			}

		}
	}
}

class EventCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EventCreationException() {
		this("");
	}

	EventCreationException(String s) {
		super(s);
	}

}
