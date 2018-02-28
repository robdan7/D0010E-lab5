package simulator;

import java.util.*;

import market.modifiers.OpeningEvent;
import simulator.modifiers.Event;
//git@github.com/robdan7/D0010E-lab5.git
import simulator.queue.EventQueue;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class State extends Observable {
	private boolean flag; // emergency break
	private float time;	// Event-time
	private EventQueue eventQueue;
	
	/**
	 * Constructor State that creates the state
	 */
	public State() {
		setFlag(false);
		time = new StateEvent(0).getTime();
	}
	
	/*
	 * Set flag
	 * 
	 * @param boolean True if simulator is to be terminated
	 */
	public void setFlag(boolean f) {
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
		
		protected StateEvent(float time) {
			super(time);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void action(EventQueue eventQueue, State state) {	}
	}
}
