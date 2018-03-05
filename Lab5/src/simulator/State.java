package simulator;

import java.util.*;

import market.modifiers.OpeningEvent;
import simulator.modifiers.Event;
//git@github.com/robdan7/D0010E-lab5.git
import simulator.queue.EventQueue;

/**
 * This class represents a state in the simulator. Its purpose is to be modified
 * throughout the simulation, and notifying its views.
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class State extends Observable {
	private boolean flag; // emergency break

	private EventQueue eventQueue;

	/**
	 * Constructor State that creates the state
	 */
	public State() {
		this.flag = true;
	}

	/*
	 * Set this state to non-active.
	 */
	public void endState() {
		flag = false;
	}

	/**
	 * Return the flag for this state. A state is active as long as the flag is
	 * true.
	 * 
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

	@Override
	public abstract String toString();
	
	
	public abstract void notifyFromEvent(Event event);
}
