package market.checkout;

public class CheckoutFactory {
	private Checkout[] checkouts;
	
	public CheckoutFactory(int numberOfCheckouts) {
		this.checkouts = new Checkout[numberOfCheckouts];
	}
	
	/**
	 * See if all checkouts are full.
	 * @return
	 */
	public boolean isFull() {
		boolean flag = true;
		for (Checkout c: this.checkouts) {
			if (!c.isUsed()) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * Request a checkout for a customer. The checkout is then set to used.
	 * @return E checkout. <b>null</b> is returned if all checkouts are used.
	 */
	public Checkout requestCheckout() {
		for (Checkout c : this.checkouts) {
			if (!c.isUsed()) {
				c.setUsed();
				return c;
			}
		}
		return null;
	}
}
