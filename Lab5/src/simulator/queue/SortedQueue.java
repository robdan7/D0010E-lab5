package simulator.queue;

public class SortedQueue<E extends SortedItem> extends QUEUE<E>{

	@Override
	public E next() {
		return super.removeFirst();
	}

	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
