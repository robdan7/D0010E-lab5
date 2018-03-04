package simulator;

import simulator.queue.EventQueue;
import simulator.queue.QUEUE;
import simulator.modifiers.Event;

import simulator.stream.*;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class Simulator {
	private EventQueue queue; // Event queue
	private final State state; // Current state

	
	/**
	 * Create a general simulator.
	 * @param state - The state to use.
	 * @param eventQueue - The eventQueue to use.
	 * @param startEvent - The start event.
	 * @param lowerbound - ?
	 * @param upperbound - ?
	 * @param seed - ?
	 */
	public Simulator(State state, EventQueue eventQueue, Event startEvent, double lowerbound, double upperbound, long seed) {
		this.state = state;
		queue = eventQueue;
		queue.addEvent(startEvent);
	}
	
	/**
	 * Runs the simulator. It will keep running while the state is active.
	 * 
	 * @throws IndexOutOfBoundsException - The queue has no more elements.
	 */
	public void run() throws IndexOutOfBoundsException{
		while (this.state.getFlag()) {
			if (this.queue.isEmpty()) {
				throw new IndexOutOfBoundsException("No ending event has been added!");
			}
			queue.nextEvent(this.state);
		}
	}
}
