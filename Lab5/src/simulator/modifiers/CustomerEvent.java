package simulator.modifiers;



public abstract class CustomerEvent extends Event {
	Customer customer;
	CustomerEvent(Customer c,float time){
		super(time);
		this.customer=c;
	}
}
class Customer{
	
}
