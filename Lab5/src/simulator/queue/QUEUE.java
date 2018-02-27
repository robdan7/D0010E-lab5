package simulator.queue;

public abstract class QUEUE<E> {
	private final Node<E> start;
	private int length = 0;

	QUEUE() {
		this.start = new Node<E>(null);
	}
	
	protected final E removeFirst() throws IndexOutOfBoundsException {
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> n = this.start.getNext();
		this.start.removeNext();
		return n.getItem();
	}

	public abstract E next();

	public abstract void add(E item);

	public boolean isEmpty() {
		return !this.start.hasNext();
	}

	public int size() {
		return this.length;
	}
}
