package market.modifiers;

import market.DataPackage;
import market.MarketState;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.modifiers.Start;
import simulator.modifiers.Stop;
import simulator.queue.EventQueue;

/**
 * This class is responsible for opening the market, creating new events, and
 * creating the final events for the simulation.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class OpeningEvent extends Start {
	private double closingTime;
	private DataPackage data;

	/**
	 * Create the starting event of the market. This events will create one arrival
	 * event, one closing event and one event for exiting the simulation.
	 * 
	 * @param eventQueue
	 *            - The eventQueue to use.
	 * @param closingTime
	 *            - Closing time for the market.
	 * @param endOfWorldTime
	 *            - Time when the simulation ends.
	 */
	public OpeningEvent(EventQueue eventQueue, double closingTime, double endOfWorldTime, DataPackage data) {
		super(endOfWorldTime);
		this.closingTime = closingTime;
		this.data = data;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState)state).notifyFromEvent(this);
		((MarketState) state).setOpen();
		eventQueue.addEvent(new ClosingEvent(this.closingTime));
		eventQueue.addEvent(new ArrivalEvent(((MarketState) state).nextArrivalTime(super.getTime()), this.data));
		super.action(eventQueue, state);
	}

	@Override
	public String toString() {
		return "Start";
	}

}
