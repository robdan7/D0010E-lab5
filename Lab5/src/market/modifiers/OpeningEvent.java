package market.modifiers;

import market.MarketState;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * This class is responsible for opening the market, creating new events, and
 * creating the final events for the simulation.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class OpeningEvent extends Event {
	private double closingTime, endOfWorldTime;

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
	public OpeningEvent(EventQueue eventQueue, double closingTime, double endOfWorldTime) {
		super(0);
		this.closingTime = closingTime;
		this.endOfWorldTime = endOfWorldTime;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		if (!(state instanceof MarketState)) {
			throw new ClassCastException();
		}
		((MarketState) state).setOpen();
		eventQueue.addEvent(new ClosingEvent(this.closingTime));
		eventQueue.addEvent(new ArrivalEvent(((MarketState) state).nextArrivalTime(super.getTime())));

		// Break event.
		eventQueue.addEvent(new Event(this.endOfWorldTime) {

			@Override
			public void action(EventQueue eventQueue, State state) {
				((MarketState) state).endState();
			}

		});
	}

}
