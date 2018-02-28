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
	private float time;

	protected Event(float time) {
		this.time = time;
	}

	public float getTime() {
		return time;
	}

	@Override
	public boolean largerThan(SortedItem item) throws ClassCastException {
		if (!(item instanceof Event))
			throw new ClassCastException();
		return ((Event) item).getTime() > this.getTime();
	}

	public abstract void action(EventQueue eventQueue, State state);
}