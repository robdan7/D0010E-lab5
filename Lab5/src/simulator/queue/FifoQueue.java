package simulator.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 * @param <E>
 */

public class FifoQueue<E> extends QUEUE<E>{
	private Node<E> last;
	Observable iteratorObservable;
	
	@SuppressWarnings("deprecation")
	public FifoQueue() {
		super(new Node<E>(null));
		this.last = super.getFirst();
		
		iteratorObservable = new Observable() {
			@Override
			public void notifyObservers() {
				this.setChanged();
				super.notifyObservers();
			}
		};
	}

	@Override
	public E next() throws NullPointerException{
		if (this.isEmpty()) {
			throw new NullPointerException();
		}
		if (super.getFirst().getNext().equals(this.last)) {
			this.last = super.getFirst();
		}
		E item = super.getFirst().removeNext();
		super.subtractSize();
		this.change();
		return item;
	}

	@Override
	public void add(E item) {
		this.last.insertNext(new Node<E>(item));
		this.last = this.last.getNext();
		super.addSize();
		this.change();
	}
	
	private void change() {
		this.iteratorObservable.notifyObservers();
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
			private boolean changed = false;
			private Node<E> node = getFirst();
			Observer o = new Observer() {
				@Override
				public void update(Observable arg0, Object arg1) {
					changed = true;
				}
			};
			
			@Override
			public boolean hasNext() {
				return node.hasNext();
			}

			@Override
			public E next() {
				if (this.changed || !this.hasNext()) {
					throw new NoSuchElementException();
				}
				node = node.getNext();
				return node.getItem();
			}	
		};
	}
}
