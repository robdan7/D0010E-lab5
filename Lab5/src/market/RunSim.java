package market;

import market.modifiers.OpeningEvent;
import simulator.Simulator;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class RunSim {

	/**
	 * 
	 * @param maxCustomers
	 * @param maxCheckouts
	 * @param arrivalTime
	 * @param checkoutTime
	 * @param pickingTime
	 * @param closingTime
	 * @param endTime
	 * @param seed
	 */
	public RunSim(DataPackage data,  double endTime, boolean useView) {
		
		MarketState s = new MarketState(data);
		MarketView view = new MarketView(s);
		if (useView) {
			s.addObserver(view);
		}
		if (useView) view.printHeader();
		EventQueue q = new EventQueue();
		Event open = new OpeningEvent(q, data.getClosingTime(), endTime, data);
		
		Simulator sim = new Simulator(s, q, open, 0, 0, 0);
		sim.run();
		if(useView) view.printFooter();
	}
}
