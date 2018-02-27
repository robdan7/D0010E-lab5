package simulator.queue;

/**
 * This class represents a queue with item priority. All items added must implements SortedItem and must be of the same class.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
class SortedQueue<E extends SortedItem> extends QUEUE<E>{
	private int size = 0;
	
	SortedQueue() {
		super(new SortedNode<E>(null));
	}

	@Override
	public E next() {
		this.subtractSize();
		return super.removeFirst();
	}

	@Override
	public void add(E item) {
		this.addSize();
		((SortedNode<E>)super.getFirst()).findLargerPriority(item).insertNext(new SortedNode<E>(item));
	}

	@Override
	public int size() {
		return this.size;
	}
	
	private void addSize() {
		this.size++;
	}
	
	private void subtractSize() {
		this.size--;
	}

	@Override
	public String toString() {
		return super.getFirst().getNext().toString();
	}
}
