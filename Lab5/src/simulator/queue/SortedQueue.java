package simulator.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

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
		this.change();
		return item;
	}

	@Override
	public void add(E item) {
		super.addSize();
		if (item == null) {
			throw new IllegalArgumentException();
		}
		((SortedNode<E>)super.getFirst()).findLargerPriority(item).insertNext(new SortedNode<E>(item));
		this.change();
	}
	
	private void change() {
		
	}


	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		}
		return "[" + super.getFirst().getNext().toString() + "]";
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node<E> node = getFirst();
			private boolean changed;
			Observer o = new Observer() {
				@Override
				public void update(Observable arg0, Object arg1) {
					changed = true;
				}
			};
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public E next() {
				if (this.changed || !this.hasNext()) {
					throw new NoSuchElementException();
				}
				this.node = this.node.getNext();
				return node.getItem();
			}
			
		};
	}
}
