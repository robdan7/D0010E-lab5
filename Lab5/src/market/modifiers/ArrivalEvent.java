package market.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class ArrivalEvent extends CustomerEvent {

	ArrivalEvent(Customer c, float time) {
		super(c, time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		eventQueue.addEvent(new QueueEvent(customer, state));
		if(true/*store not Closed*/) {
			eventQueue.addEvent(new ArrivalEvent(new Customer()/*New Customer*/,2/*Next Customer time*/));
		}
	}
}
