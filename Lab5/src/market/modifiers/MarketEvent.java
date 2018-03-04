package market.modifiers;

import market.customer.Customer;
import simulator.modifiers.Event;
/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public abstract class MarketEvent extends Event {
	private Customer customer;
	MarketEvent(Customer c,double time){
		super(time);
		this.customer=c;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}
