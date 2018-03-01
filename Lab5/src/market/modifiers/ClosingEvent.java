package market.modifiers;

import market.MarketState;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * This class is responsible for closing the market.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class ClosingEvent extends Event {

	ClosingEvent(double time) {
		super(time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).setClosed();
	}

}
