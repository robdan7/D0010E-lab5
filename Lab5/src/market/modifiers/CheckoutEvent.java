package market.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class CheckoutEvent extends CustomerEvent {

	CheckoutEvent(Customer c,float time) {
		super(c,time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		//Make sure customer has left
	}

}
