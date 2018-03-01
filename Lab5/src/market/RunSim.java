package market;

import market.modifiers.OpeningEvent;
import simulator.Simulator;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

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
	public RunSim(int maxCustomers, int maxCheckouts, double arrivalTime, double[] checkoutTime, double[] pickingTime,
			double closingTime, double endTime, long seed) {
		
		
		MarketState s = new MarketState(maxCustomers, arrivalTime, checkoutTime, pickingTime, maxCheckouts, seed);
		MarketView view = new MarketView(s);
		EventQueue q = new EventQueue();
		Event open = new OpeningEvent(q, closingTime, endTime);
		Simulator sim = new Simulator(s, q, open, 0, 0, 0);
	}
}
