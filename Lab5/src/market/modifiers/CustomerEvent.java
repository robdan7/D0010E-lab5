package market.modifiers;

import simulator.modifiers.Event;

public abstract class CustomerEvent extends Event {
	Customer customer;
	CustomerEvent(Customer c,float time){
		super(time);
		this.customer=c;
	}
}
class Customer{
	
}
