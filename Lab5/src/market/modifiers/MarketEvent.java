package market.modifiers;

import market.customer.Customer;
import simulator.modifiers.Event;

public abstract class MarketEvent extends Event {
	private Customer customer;
	private double time;
	MarketEvent(Customer c,double time){
		super(time);
		this.customer=c;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}
