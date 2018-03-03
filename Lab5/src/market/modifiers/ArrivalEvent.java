package market.modifiers;

import market.DataPackage;
import market.MarketState;
import market.customer.Customer;
import market.customer.Customerfactory;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;
import simulator.queue.FifoQueue;
import simulator.queue.QUEUE;

/**
 * This class represents a customer arrival. It is also responsible for creating new customers.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */

public class ArrivalEvent extends MarketEvent {
	private Customerfactory factory;
	private DataPackage data;

	/**
	 * Create the first arrivalEvent for new customers.
	 * 
	 * @param time
	 *            - The time a customer arrives.
	 */
	ArrivalEvent(double time, DataPackage data) {
		this(new Customerfactory(), time, data);
	}


	/**
	 * Create an event for new customers.
	 * 
	 * @param factory - The customer factory used by all previous arrivals.
	 * @param checkoutQueue - The common queue for future checkouts.
	 * @param time - The arrival time.
	 */
	private ArrivalEvent(Customerfactory factory, double time, DataPackage data) {
		super(factory.newcustomer(), time);
		this.factory = factory;
		this.data = data;
	}

	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).notifyFromEvent(this);
		eventQueue.addEvent(new QueueEvent(this.factory.newcustomer(),
				((MarketState) state).nextToQueueTime(super.getTime()), data));
		if (((MarketState) state).isOpen()) {
			// Create a new arrival event for the next customer.
			double t = ((MarketState) state).nextArrivalTime(super.getTime()); // The time to arrive.
			
			// Create the next arrival event.
			eventQueue.addEvent(new ArrivalEvent(this.factory, t, this.data));
			
			// See if the market if full or not.
			if (!data.marketIsFull()) {
				data.addCustomer();
				
				// The time until the customer is at the queue.
				t = ((MarketState)state).nextToQueueTime(super.getTime());
				
				// Add this customer to the ckeckout queue.
				eventQueue.addEvent(new QueueEvent(super.getCustomer(), t, data));
			} else {
				// The market is full. We lost a customer.
				data.addMissedCustomer();
			}

		}
	}

	@Override
	public String toString() {
		return "Ankomst";
	}
}