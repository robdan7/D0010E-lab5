package simulator.modifiers;

import market.MarketState;
import simulator.State;
import simulator.queue.EventQueue;

public class Stop extends Event{
	public Stop(double time) {
		super(time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		((MarketState)state).notifyFromEvent(this);
		((MarketState) state).endState();
	}

	@Override
	public String toString() {
		return "Stop";
	}
}
