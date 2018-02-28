package market.checkout;

public class Checkout {
	private boolean isUsed = false;
	
	Checkout() {
		
	}
	
	void setUsed() {
		this.isUsed = true;
	}
	
	boolean isUsed() {
		return this.isUsed;
	}
	
	public void setUnUsed() {
		this.isUsed = false;
	}
}
