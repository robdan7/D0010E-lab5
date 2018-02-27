package simulator.modifiers;

import simulator.State;
import simulator.queue.EventQueue;
import simulator.queue.SortedItem;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class Event implements SortedItem{

	public float getTime() {
		return 0;
	}

	@Override
	public boolean largerThan(SortedItem item) throws ClassCastException {
		return true;
	}
	
	public abstract void run(EventQueue eventQueue, State state);
}
