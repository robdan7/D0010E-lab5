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
public class View implements Observer{
	private State state;
	
	/*PrintWriter settings*/
	private PrintWriter write;
	private final String filename = "Rapport.txt";
	private final String charset = "UTF-8";
	private final String col = "      "; // column margin (six blank spaces)
	
	public View(State state) {
		this.state = state;
		state.addObserver(this);
		
		try {
			this.write = new PrintWriter(filename, charset);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeHeader() {
		write.println("PARAMETRAR");
		write.println("==========");
		write.println("Antal kassor, N..........: " + "antal");
		write.println("Max som ryms, M..........: " + "antal");
		write.println("Ankomsthastighet, lambda..: " + "antal");
		write.println("Plocktider, [P_min..Pmax]: " + "[min..max]");
		write.println("Betaltider, [K_min..Kmax]: " + "[min..max]");
		write.println("Frö, f...................: " + "tal");
		write.println(); // &nbsp;
		write.println("FÖRLOPP");
		write.println("==========");
		write.println(" Tid   Händelse"+col+"Kund"+"?"+col+"led"+col
				+"ledT"+col+"I"+col+"$"+":-("+col+"köat"+"köT"+col+"köar"+ col
				+"[Kassakö..]");
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
