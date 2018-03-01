package market;

import market.modifiers.OpeningEvent;
import simulator.Simulator;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

public class RunSim {

	public RunSim(int maxCustomers, int maxCheckouts, double arrivalTime, double[] checkoutTime, double[] pickingTime,
			double closingTime, double endTime, long seed) {
		
		
		MarketState s = new MarketState(maxCustomers, arrivalTime, checkoutTime, pickingTime, maxCheckouts, seed);
		EventQueue q = new EventQueue();
		Event open = new OpeningEvent(q, closingTime, endTime);
		Simulator sim = new Simulator(s, q, open, 0, 0, 0);
	}
}
