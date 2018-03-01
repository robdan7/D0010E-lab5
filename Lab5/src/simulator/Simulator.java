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
	@Deprecated
	private ExponentialRandomStream expRand;
	@Deprecated
	private UniformRandomStream uniRand;
	@Deprecated
	private long seed;

	
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
		this.seed = seed;
		expRand = new ExponentialRandomStream(seed);
		uniRand = new UniformRandomStream(lowerbound, upperbound, seed);
		View view = new View(state);
	}
	
	/**
	 * Runs the simulator. It will keep running while the state is active.
	 * 
	 * @param start start event
	 * @throws IndexOutOfBoundsException - The queue has no more elements.
	 */
	public void run(Event start) throws IndexOutOfBoundsException{
		while (this.state.getFlag()) {
			if (this.queue.isEmpty()) {
				throw new IndexOutOfBoundsException("No ending event has been added!");
			}
			queue.nextEvent(this.state);
		}
	}
	
	/**
	 * 
	 * @return - next uniformed random double
	 */
	public double getUniRand() {
		return uniRand.next();
	}
	
	/**
	 * 
	 * @return - next exponential random double
	 */
	public double getExpRand() {
		return expRand.next();
	}
	
	/**
	 * 
	 * @return - seed
	 */
	public long getSeed() {
		return this.seed;
	}
}
