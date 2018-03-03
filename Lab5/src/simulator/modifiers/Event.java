package simulator.modifiers;

import simulator.State;
import simulator.queue.EventQueue;
import simulator.queue.SortedItem;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class Event implements SortedItem {
	private double time;

	/**
	 * 
	 * @param time - The time for this event.
	 */
	protected Event(double time) {
		this.time = time;
	}

	public double getTime() {
		return time;
	}

	@Override
	public boolean higherPriority(SortedItem item) throws ClassCastException {
		if (!(item instanceof Event)) {
			throw new ClassCastException();
		}
		return ((Event) item).getTime() > this.getTime();
	}

	public abstract void action(EventQueue eventQueue, State state);
	
	@Override
	public abstract String toString();
}