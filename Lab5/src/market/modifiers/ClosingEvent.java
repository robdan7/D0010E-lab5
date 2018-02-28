package market.modifiers;

import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

public class ClosingEvent extends Event {

	ClosingEvent(float time) {
		super(time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		eventQueue.addEvent(new ShutdownEvent(999.0f));	
	}

}
