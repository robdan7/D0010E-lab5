package simulator;

import simulator.queue.EventQueue;
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
	private ExponentialRandomStream expRand;
	private UniformRandomStream uniRand;
	private long seed;

	
	/**
	 * Constructor that creates simulator
	 */
	public Simulator(double lowerbound, double upperbound, long seed) {
		state = new State();
		queue = new EventQueue();
		this.seed = seed;
		expRand = new ExponentialRandomStream(seed);
		uniRand = new UniformRandomStream(lowerbound, upperbound, seed);
		View view = new View(state);
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
