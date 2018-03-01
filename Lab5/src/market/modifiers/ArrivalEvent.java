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
	private Customer customer;

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
		this.customer = this.factory.newcustomer();
		this.customerQueue = checkoutQueue;
	}

	public void action(EventQueue eventQueue, State state) {
		if ((state instanceof MarketState)) {
			throw new ClassCastException();
		}
		eventQueue.addEvent(new QueueEvent(this.factory.newcustomer(), this.customerQueue,
				((MarketState) state).nextToQueueTime(super.getTime())));
		if (((MarketState) state).isOpen()) {
			// Create a new arrival event for the next customer.
			double t = ((MarketState) state).nextArrivalTime(super.getTime()); // The time to arrive.
			
			// Create the next arrival event.
			eventQueue.addEvent(new ArrivalEvent(this.factory, this.customerQueue, t));
			
			// See if the market if full or not.
			if (!((MarketState)state).marketIsFull()) {
				((MarketState) state).addCustomer();
				
				// The time until the customer is at the queue.
				t = ((MarketState)state).nextToQueueTime(super.getTime());
				
				// Add this customer to the ckeckout queue.
				eventQueue.addEvent(new QueueEvent(this.customer, customerQueue, t));
			} else {
				// The market is full. We lost a customer.
				((MarketState)state).addMissedCustomer();
			}
		}
	}
}