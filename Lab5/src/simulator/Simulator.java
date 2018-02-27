package simulator;

import simulator.queue.EventQueue;
import simulator.modifiers.Event;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class Simulator {
	private EventQueue queue; // Event queue
	private final State state; // Current state
	
	/**
	 * Constructor that creates simulator
	 */
	public Simulator() {
		state = new State();
		queue = new EventQueue();
	}
	
	/**
	 * Runs the simulator. Will keep running while EventQuee is not empty.
	 * 
	 * @param start start event
	 */
	public void run(Event start) {
		while (!queue.isEmpty()  && !this.state.getFlag()) {
			queue.nextEvent(this.state);
		}
	}
}
