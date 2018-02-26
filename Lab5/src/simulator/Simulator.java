package simulator;
/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Ronbin Danielsson & Zerophymyr Falk
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
	 * Runs the simulator
	 * 
	 * @param start start event
	 */
	public void run(Event start) {
		
	}
}
