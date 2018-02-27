package simulator.queue;

public class SortedNode<E extends SortedItem> extends Node<E>{

	public SortedNode(E item) {
		super(item);
	}
	
 
	private Node<E> findsmallerthan(E item) {
		if (!this.hasNext()) {
			return this;
		} else if (!((SortedItem) this.getNext().getItem()).smallerThan(item)) {
			return this;
		} else {
			return ((SortedNode<E>)this.getNext()).findsmallerthan(item);
		}
	}
	
	public Node<E> getNext() {
		return super.getNext();
	}
	
	/**
	 * Pre: n is instance of SortedNode.
	 */
	void insertNext(Node<E> n) {
		super.insertNext((SortedNode<E>)n);
	}

}
