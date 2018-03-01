package simulator.queue;

/**
 * This class is a special node for determining the priority of the item. A node
 * of this kind can be put in the queue at a specific position determined by the
 * "size" of its item.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
class SortedNode<E extends SortedItem> extends Node<E> {

	SortedNode(E item) {
		super(item);
	}

	/**
	 * Find a node with a higher priority item than the next one. The determined priority
	 * @param item
	 * @return
	 */
	Node<E> findLargerPriority(E item) {
		if (!this.hasNext()) {
			return this;
		} else if (!((SortedItem) this.getNext().getItem()).higherPriority(item)) {
			return this;
		} else {
			return ((SortedNode<E>) this.getNext()).findLargerPriority(item);
		}
	}
}
