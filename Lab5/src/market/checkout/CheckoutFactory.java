package market.checkout;

/**
 * This class creates new checkouts and keeps track of available checkouts etc.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 */
public class CheckoutFactory {
	private Checkout[] checkouts;
	
	public CheckoutFactory(int numberOfCheckouts) {
		this.checkouts = new Checkout[numberOfCheckouts];
		for (int i = 0; i < this.checkouts.length; i++) {
			this.checkouts[i] = new Checkout();
		}
	}
	
	/**
	 * See if all checkouts are full.
	 * @return
	 */
	public boolean isFull() {
		boolean flag = true;
		for (Checkout c: this.checkouts) {
			if (!c.isOpened()) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public int getClosedcheckouts() {
		int i = 0;
		for (Checkout c : this.checkouts) {
			i += !c.isOpened() ? 1 : 0;
		}
		return i;
	}
	
	public double getTimeClosed(double time) {
		double resultTime = 0;
		for (Checkout c : this.checkouts) {
			resultTime += c.getTimeClosed(time);
		}
		
		return resultTime;
	}
	
	/**
	 * Request a checkout for a customer. The checkout is then set to used.
	 * @param time - The current time.
	 * @return E checkout. <b>null</b> is returned if all checkouts are used.
	 * @throws NullPointerException - All checkouts are used.
	 */
	public Checkout requestCheckout(double time) throws NullPointerException{
		for (Checkout c : this.checkouts) {
			if (!c.isOpened()) {
				c.setUsed(time);
				return c;
			}
		}
		throw new NullPointerException();
	}
}
