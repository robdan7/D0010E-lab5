package simulator.queue;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson, Robin Danielsson, Zerophymyr Falk
 *
 */
public interface SortedItem {
	/**
	 * Compare two items to determine if this one has a larger priority than the other one.
	 * @param item
	 * @return <b>true</b> if this instance has a higher priority. <b>false</b> if it is equal or less.
	 * @throws ClassCastException The other item is not an instance of this class.
	 */
	boolean higherPriority(SortedItem item) throws ClassCastException;
}