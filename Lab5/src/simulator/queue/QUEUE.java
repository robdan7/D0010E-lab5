package simulator.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

/**
 * This abstract class is a stencil for general queues.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class QUEUE<E> implements Iterable<E>{
	private final Node<E> start;
	private int size = 0;
	private Observable iteratorObservable;

	protected final Node<E> getFirst() {
		return this.start;
	}
	
	/**
	 * Initiate the first node with a desired instance.
	 * @param n - The node to store locally.
	 */
	@SuppressWarnings("deprecation")
	public QUEUE(Node<E> n) {
		this.start = n;
		iteratorObservable = new Observable() {
			@Override
			public void notifyObservers() {
				super.setChanged();
				super.notifyObservers();
			}
		};
	}

	/**
	 * 
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	protected final E removeFirst() throws IndexOutOfBoundsException {
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return this.start.removeNext();
	}

	/**
	 * 
	 * @return <b>true</b> if this queue is empty, <b>false</b> if not.
	 */
	public boolean isEmpty() {
		return !this.start.hasNext();
	}

	/**
	 * Pull the next item from the queue. The pulled item will no longer exist as an
	 * item internally.
	 * 
	 * @return The next item in order.
	 */
	public abstract E next() throws NullPointerException;

	/**
	 * Add an item to the queue.
	 * 
	 * @param item
	 *            - The generic item to add.
	 */
	public abstract void add(E item);

	/**
	 * Get the current size of this queue. The size is 0 if no items have been
	 * added.
	 * 
	 * @return - The queue size.
	 */
	public int size() {
		return this.size;
	}
	
	protected void addSize() {
		this.size++;
	}
	
	protected void subtractSize() {
		this.size--;
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public Iterator<E> iterator() {
		return new QueueIterator(this.iteratorObservable);
	}
	
	/**
	 * A node has been removed or added to this queue. this must be called in order
	 * for iterators to work properly.
	 */
	protected final void change() {
		this.iteratorObservable.notifyObservers();
	}
	
	/**
	 * Create an iterator for the queue. If the queue somehow is changed during an
	 * iteration, the iteration is locked.
	 * 
	 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
	 *
	 */
	protected class QueueIterator implements Iterator<E> {
		Node<E> node = getFirst();
		private boolean changed;
		
		@SuppressWarnings("deprecation")
		private Observer o = new Observer() {
			@Override
			public void update(Observable arg0, Object arg1) {
				changed = true;
			}
		};
		
		@SuppressWarnings("deprecation")
		protected QueueIterator(Observable observable) {
			observable.addObserver(this.o);
		}
		
		@Override
		public boolean hasNext() {
			return node.hasNext();
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
