package simulator.modifiers;

import market.MarketState;
import market.modifiers.ArrivalEvent;
import market.modifiers.ClosingEvent;
import simulator.State;
import simulator.queue.EventQueue;

public class Start extends Event{
	private double endOfWorldTime;
	public Start(double endTime) {
		super(0);
		this.endOfWorldTime = endTime;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		// Break event.
		eventQueue.addEvent(new Stop(this.endOfWorldTime));
	}

	@Override
	public String toString() {
		return "Start";
	}

}
