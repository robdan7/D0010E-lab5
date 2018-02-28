package market.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class CheckoutEvent extends CustomerEvent {

	CheckoutEvent(Customer c) {
		super(c,5.0f);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		//Make sure customer has left
	}

}
