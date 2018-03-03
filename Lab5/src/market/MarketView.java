package market;

import java.util.Observable;
import java.util.Observer;
import simulator.State;
import simulator.View;


public class MarketView extends View{
	State state;
	
	MarketView(State observable){
		super(observable);
	}
	
	void printHeader() {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........: " + "antal");
		System.out.println("Max som ryms, M..........: " + "antal");
		System.out.println("Ankomsthastighet, lambda..: " + "antal");
		System.out.println("Plocktider, [P_min..Pmax]: " + "[min..max]");
		System.out.println("Betaltider, [K_min..Kmax]: " + "[min..max]");
		System.out.println("Frö, f...................: " + "tal");
		System.out.println(); // &nbsp;
		System.out.println("FÖRLOPP");
		System.out.println("==========");
		System.out.println(" Tid\tHändelse\tKund\t+col\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(arg0.toString());
	}
}
