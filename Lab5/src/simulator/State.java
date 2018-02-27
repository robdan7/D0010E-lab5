package simulator;

import java.util.*;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class State extends Observable {
	private boolean flag; // emergency break
	private float time;	// Event-time
	
	/**
	 * Constructor State that creates the state
	 */
	public State() {
		setFlag(false);
		time = new StateEvent().getTime();
	}
	
	/*
	 * Set flag
	 * 
	 * @param boolean True if simulator is to be terminated
	 */
	void setFlag(boolean f) {
		flag = f;
	}
	
	boolean getFlag() {
		return flag;
	}
	
	/**
	 * Notify method that can be called from other classes.
	 */
	public void observers() {
		setChanged();
		notifyObservers();
	}
	
	
	/*
	 * Private inner class inherit Event
	 */
	private class StateEvent extends Event{
		
		@Override
		public void run(EventQueue eventQueue, State state) {	}
	}
}
