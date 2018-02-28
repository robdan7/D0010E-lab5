package simulator.queue;

/**
 * This class serves as a container for queue items. One node can have a pointer
 * to the next node, thus forming a chain of items.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 * @param <E>
 */
class Node<E> {
	private E item;
	private Node<E> next;

	public Node(E item) {
		this.item = item;
	}

	/**
	 * 
	 * @return <b>true</b> if this node has a next, <b>false</b> if not.
	 */
	public boolean hasNext() {
		return this.next != null;
	}

	/**
	 * 
	 * @return - The next node in the chain. <b>Null</b> is returned if the next
	 *         node does not exist.
	 */
	public Node<E> getNext() {
		return this.next;
	}

	/**
	 * 
	 * @return - The generic item of this node.
	 */
	E getItem() {
		return this.item;
	}

	/**
	 * Removes the next node from the chain and returns the item.
	 * 
	 * @return - Generic item. <b>Null</b> is returned if the next node does not
	 *         exist or does not contain an item.
	 * @throws NullPointerException The next node does not exist.
	 */
	public E removeNext() throws NullPointerException{
		if (this.getNext() == null) {
			throw new NullPointerException();
		}
		Node<E> n = this.getNext();
		this.next = this.getNext().getNext();
		return n.getItem();
	}

	/**
	 * Insert a new node in the chain without removing any other nodes.
	 * 
	 * @param n
	 *            - The generic item to add.
	 */
	void insertNext(Node<E> n) {
		n.next = this.getNext();
		this.next = n;
	}
	
	@Override
	public String toString() {
		if (this.hasNext()) {
			return this.item + ", " + this.getNext().toString();
		}
		return this.item.toString();
	}
}
