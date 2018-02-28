package market.customer;


/**
 * This class consists of a Customer object. Each Customer has its own unique ID.
 */
public class Customer {

	private final int customerid;
	float timeinqueue; //
	/**
	 * @param customerID
	 */
	protected Customer(int customerid) {
		this.customerid = customerid;
	}
	/**
	 * 
	 * @return ID.
	 */
	public int ID() {
		return customerid;
	}
}
