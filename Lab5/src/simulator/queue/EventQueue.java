package simulator.queue;

import simulator.State;
import simulator.modifiers.Event;

/**
 * This class contains all upcoming events in the simulator.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class EventQueue {
	public QUEUE<Event> queue;
	
	public EventQueue() {
		this.queue = new SortedQueue<Event>();
	}
	
	public void addEvent(Event e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		this.queue.add(e);
	}
	
	/**
	 * 
	 * @return <b>True</b> if this queue is empty.
	 */
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
	
	/**
	 * Run the next event. The event will be removed from the queue and executed.
	 * @param state - The state to modify.
	 */
	public void nextEvent(State state) {
		this.queue.next().action(this, state);
	}
	
	public int size() {
		return this.queue.size();
	}
}
