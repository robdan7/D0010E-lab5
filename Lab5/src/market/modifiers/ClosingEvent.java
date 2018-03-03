package market.modifiers;

import market.MarketState;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * This class is responsible for closing the market.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class ClosingEvent extends MarketEvent {

	ClosingEvent(double time) {
		super(null, time);
	}
	
	@Override
	public Customer getCustomer() {
		return new Customer(-1) {
			@Override
			public String toString() {
				return "---";
			}
		};
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).notifyFromEvent(this);
		((MarketState)state).setClosed();
	}

	@Override
	public String toString() {
		return "Stänger";
	}

}
