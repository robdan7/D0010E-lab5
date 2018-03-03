package market;

import java.util.Observable;
import java.util.Observer;
import simulator.State;
import simulator.View;


public class MarketView extends View<MarketState>{
	
	MarketView(MarketState observable){
		super(observable);
	}
	
	void printHeader() {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........: " + super.getState().getData().getCheckoutAmount());
		System.out.println("Max som ryms, M..........: " + super.getState().getData().getMaxCustomers());
		System.out.println("Ankomsthastighet, lambda..: " + super.getState().getData().getArrivalTime());
		double[] pickTime = super.getState().getData().getPickingTime();
		System.out.println("Plocktider, [P_min..Pmax]: " + "[" + pickTime[0] + "..." + pickTime[1] +"]");
		double[] checkoutTime = super.getState().getData().getCheckoutTime();
		System.out.println("Betaltider, [K_min..Kmax]: " + "[" + checkoutTime[0] + "..." + checkoutTime[1] +"]");
		System.out.println("Frö, f...................: " + super.getState().getData().getSeed());
		System.out.println(); // &nbsp;
		System.out.println("FÖRLOPP");
		System.out.println("==========");
		System.out.println(" Tid\tHändelse\tKund\t+col\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
		
	}
	
	void printFooter() {
		System.out.println("\nRESULTAT\n==========");
		
		int missedCustomers = super.getState().getData().getMissedCustomers();
		int totalcustomers = super.getState().getData().getTotalCustomers();
		
		System.out.println("1)\tAv " + (totalcustomers + missedCustomers) + " handlade "  + totalcustomers + 
				" medan " + missedCustomers + " missades.");
		
		System.out.println("\n2)\tTotal tid " + super.getState().getData().getCheckoutAmount() + " kasor varit lediga: "
				+ String.format("%.2f",  super.getState().getClosedCheckoutTime()) + " te."
				+ "\n\tGenomsnittlig ledig kassatid: " + String.format("%.2f",
						super.getState().getClosedCheckoutTime() / super.getState().getData().getCheckoutAmount()) + 
				" te."
				);
		
		System.out.println("\n3)\tTotal tid " + super.getState().getData().getTotalQueuedCustomers() + " tvingats köa: " +
				String.format("%.2f", super.getState().getData().getTotalQueueTime(999)) + " te.");
		double queueTime = super.getState().getData().getTotalQueueTime(999)/super.getState().getData().getTotalQueuedCustomers();
		System.out.println("\tGenomsnittlig kötid: " + String.format("%.2f", queueTime) + " te.");

	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(arg0.toString());
	}
}
