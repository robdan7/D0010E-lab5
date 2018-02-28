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
		if(true/*store not Closed and Store not full*/) {
			eventQueue.addEvent(new ArrivalEvent(new Customer()/*New Customer*/,this.getTime()+2/*Next Customer time*/));
		}
	}
}
