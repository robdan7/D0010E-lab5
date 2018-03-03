package market.customer;
/**
 * this Class keeps count on the number of cars created.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class Customerfactory {
	
	private int customerNumber = 0; //counter
	
	/**
	 * 	
	 * @return this method will return a new customer object with a unique ID.
	 */
	public Customer newcustomer(){
		Customer c = new Customer(customerNumber);
		customerNumber++;
		return c;
	}
	/**
	 * 
	 * @return the number of cars that has currently been created.
	 */
	 int NumofCustomers(){
		return customerNumber;
	 }	
}

	
	