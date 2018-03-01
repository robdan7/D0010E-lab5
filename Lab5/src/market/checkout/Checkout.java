package market.checkout;

/**
 * This class represents a single checkout in the market.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class Checkout {
	private boolean isUsed = false;
	private double timeClosed = 0;
	private double lastTimeOpen = 0;
	
	Checkout() {
		
	}
	
	/**
	 * Get the amount of time this checkout has been closed.
	 * @return
	 */
	public double getTimeClosed() {
		return this.timeClosed;
	}
	
	/**
	 * Open this checkout at the specified time.
	 * @param d - The time it is opened.
	 */
	void setUsed(double d) {
		this.timeClosed += (d-this.lastTimeOpen);
		this.isUsed = true;
	}
	
	public boolean isUsed() {
		return this.isUsed;
	}
	
	/**
	 * CLose this checkout at the specified time.
	 * @param d - The time it is closed.
	 */
	public void setClosed(double d) {
		this.lastTimeOpen = d;
		this.isUsed = false;
	}
}
