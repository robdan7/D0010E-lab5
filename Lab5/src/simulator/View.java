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
public abstract class View implements Observer{
	
	public View(State state) {
		state.addObserver(this);
	}	

	@Override
	public abstract void update(Observable arg0, Object arg1);
}
