package simulator.queue;

public class FifoQueue<E> extends QUEUE<E>{
	private Node<E> last;
	
	public FifoQueue() {
		super(new Node<E>(null));
		this.last = super.getFirst();
	}

	@Override
	public E next() {
		E item = super.getFirst().removeNext();
		super.subtractSize();
		return item;
	}

	@Override
	public void add(E item) {
		this.last.insertNext(new Node<E>(item));
		this.last = this.last.getNext();
		super.addSize();
	}

	@Override
	public String toString() {
		return super.getFirst().toString();
	}
}
