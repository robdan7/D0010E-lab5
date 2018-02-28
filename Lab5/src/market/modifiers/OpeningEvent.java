package market.modifiers;

import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

public class OpeningEvent extends Event {

	public OpeningEvent(EventQueue eventQueue) {
		super(0);
		
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		eventQueue.addEvent(new ClosingEvent(10f/*Time Open*/));
		eventQueue.addEvent(new ArrivalEvent(new Customer()/*New Customer*/,this.getTime()+2/*Time till first Customer*/));
	}

}
