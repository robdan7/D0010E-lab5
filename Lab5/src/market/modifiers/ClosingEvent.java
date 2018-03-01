package market.modifiers;

import market.MarketState;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

public class ClosingEvent extends Event {

	ClosingEvent(double time) {
		super(time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if ((state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).setClosed();
	}

}
