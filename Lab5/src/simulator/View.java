package simulator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class View<E extends State> implements Observer{
	private E state;
	
	public View(E state) {
		this.state = state;
		state.addObserver(this);
	}
	
	protected final  E getState() {
		return this.state;
	}

	@Override
	public abstract void update(Observable arg0, Object arg1);
}
