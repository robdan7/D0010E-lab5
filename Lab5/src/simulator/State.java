package simulator;

import java.util.*;
import simulator.modifiers.Event;

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
		time = new Event().getTime();
	}
	
	/*
	 * Set flag
	 * 
	 * @param boolean True if simulator is to be terminated
	 */
	void setFlag(boolean f) {
		flag = f;
		setChanged();
		notifyObservers();
	}
	
	boolean getFlag() {
		return flag;
	}
}
