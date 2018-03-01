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
public abstract class State extends Observable {
	private boolean flag; // emergency break
	@Deprecated
	private double time;	// Event-time'
	
	private EventQueue eventQueue;
	
	/**
	 * Constructor State that creates the state
	 */
	public State() {
		this.flag = true;
		time = new StateEvent(0).getTime(); // Where is this used?
	}
	
	/*
	 * Set this state to non-active.
	 */
	public void endState() {
		flag = false;
	}
	
	/**
	 * Return the flag for this state. A state is active as long as the flag is true.
	 * @return
	 */
	public boolean getFlag() {
		return flag;
	}
	
	/**
	 * Notify method that can be called from other classes.
	 */
	public void observers() {
		setChanged();
		notifyObservers();
	}
	
	public abstract String[] sendData();
	
	@Override
	public abstract String toString();
	
	/*
	 * Private inner class inherit Event
	 */
	@Deprecated
	private class StateEvent extends Event{
		
		protected StateEvent(float time) {
			super(time);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void action(EventQueue eventQueue, State state) {	}
	}
}
