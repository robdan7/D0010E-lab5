package simulator.queue;

import simulator.State;
import simulator.modifiers.Event;

/**
 * This class represents a queue with item priority. All items added must implements SortedItem and must be of the same class.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
class SortedQueue<E extends SortedItem> extends QUEUE<E>{
	
	SortedQueue() {
		super(new SortedNode<E>(null));
	}

	@Override
	public E next() throws NullPointerException{
		if (this.isEmpty()) {
			throw new NullPointerException();
		}
		E item = super.removeFirst();
		super.subtractSize();
		return item;
	}

	@Override
	public void add(E item) {
		super.addSize();
		if (item == null) {
			throw new IllegalArgumentException();
		}
		((SortedNode<E>)super.getFirst()).findLargerPriority(item).insertNext(new SortedNode<E>(item));
	}


	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "";
		}
		return super.getFirst().getNext().toString();
	}
}
