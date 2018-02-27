package simulator.queue;

class Node<E> {
	private E item;
	private Node<E> next;
	
	public Node(E item) {
		this.item = item;
	}
	
	public boolean hasNext() {
		return this.next != null;
	}

	public Node<E> getNext() {
		return this.next;
	}
	
	
	protected E getItem() {
		return this.item;
	}
	

	public E removeNext() {
		if (this.getNext() == null) {
			return null;
		}
		Node<E> n = this.getNext();
		this.next = this.getNext().getNext();
		return n.getItem();
	}
	

	@SuppressWarnings("unused")
	void insertNext(Node<E> n) {
		n.next = this.getNext();
		this.next = n;
	}
}
