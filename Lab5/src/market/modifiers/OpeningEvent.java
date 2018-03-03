package market.modifiers;

import market.DataPackage;
import market.MarketState;
import market.customer.Customer;
import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * This class is responsible for opening the market, creating new events, and
 * creating the final events for the simulation.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class OpeningEvent extends MarketEvent {
	private double closingTime, endOfWorldTime;
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
		super(null, 0);
		this.closingTime = closingTime;
		this.endOfWorldTime = endOfWorldTime;
		this.data = data;
	}
	
	@Override
	public Customer getCustomer() {
		return new Customer(-1) {
			@Override
			public String toString() {
				return "";
			}
		};
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

		// Break event.
		eventQueue.addEvent(new Event(this.endOfWorldTime) {

			@Override
			public void action(EventQueue eventQueue, State state) {
				((MarketState) state).endState();
			}

			@Override
			public String toString() {
				return "Stop";
			}

		});
	}

	@Override
	public String toString() {
		return "Start";
	}

}
