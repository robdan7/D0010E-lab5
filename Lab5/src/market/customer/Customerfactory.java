package market.customer;
/**
	 * 
	 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
	 * 
	 * this Class keeps count on the number of cars created.
	 */
public class Customerfactory {
	
	private int customersnumber = 0; //counter
	
	/**
	 * 	
	 * @return this method will return a new customer object with a unique ID.
	 */
	public Customer newcustomer(){
		customersnumber++;
		return new Customer(customersnumber);
	}
	/**
	 * 
	 * @return the number of cars that has currently been created.
	 */
	 int NumofCustomers(){
		return customersnumber;
	 }	
}

	
	