package market;

import java.util.Observable;
import java.util.Observer;
import simulator.State;


public class MarketView implements Observer {
	State state;
	
	MarketView(State observable){
		observable.addObserver(this);
		this.state=observable;
		this.printHeader();
	}
	
	private static void printHeader() {
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
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		printHeader();
	}
	

}
