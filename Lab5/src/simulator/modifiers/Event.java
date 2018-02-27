package simulator.modifiers;

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
	
	public abstract void run();
}
