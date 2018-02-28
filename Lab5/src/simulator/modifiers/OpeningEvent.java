package simulator.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class OpeningEvent extends Event {

	public OpeningEvent(EventQueue eventQueue) {
		super(0);
		
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		eventQueue.addEvent(new ClosingEvent(10f/*Time Open*/));
		eventQueue.addEvent(new ArrivalEvent(new Customer()/*New Customer*/,2/*Time till first Customer*/));
	}

}
