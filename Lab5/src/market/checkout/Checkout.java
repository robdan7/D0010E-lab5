package market.checkout;

/**
 * This class represents a single checkout in the market.
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public class Checkout {
	private boolean isOpened = false;
	private double timeClosed = 0;
	private double lastTimeOpen = 0;
	
	Checkout() {
		
	}
	
	/**
	 * Get the amount of time this checkout has been closed.
	 * @param time - Current time.
	 * @return
	 */
	public double getTimeClosed(double time) {
		if (this.isOpened()) {
			return this.timeClosed;
		} else {
			return this.timeClosed + (time-this.lastTimeOpen);
		}
	}
	
	/**
	 * Open this checkout at the specified time.
	 * @param d - The time it is opened.
	 */
	void setUsed(double d) {
		this.timeClosed += (d-this.lastTimeOpen);
		this.isOpened = true;
	}
	
	public boolean isOpened() {
		return this.isOpened;
	}
	
	/**
	 * CLose this checkout at the specified time.
	 * @param d - The time it is closed.
	 */
	public void setClosed(double d) {
		this.lastTimeOpen = d;
		this.isOpened = false;
	}
}
