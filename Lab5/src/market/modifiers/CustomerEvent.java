package market.modifiers;

import market.customer.Customer;
import simulator.modifiers.Event;

@Deprecated
public abstract class CustomerEvent extends Event {
	protected Customer customer;
	CustomerEvent(Customer c,float time){
		super(time);
		this.customer=c;
	}
}
