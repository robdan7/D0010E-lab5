package simulator.queue;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class QUEUE<E> {
	private final Node<E> start;
	private int size = 0;

	protected final Node<E> getFirst() {
		return this.start;
	}
	
	/**
	 * Initiate the first node with a desired instance.
	 * @param n - The node to store locally.
	 */
	public QUEUE(Node<E> n) {
		this.start = n;
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
}
