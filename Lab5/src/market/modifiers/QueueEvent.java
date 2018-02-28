package market.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class QueueEvent extends CustomerEvent {

	QueueEvent(Customer c,State state) {
		super(c,5);
		//Do checkout Queue
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		eventQueue.addEvent(new CheckoutEvent(customer,this.getTime()+5.0f/*Time to checkout*/));
	}
	
}
