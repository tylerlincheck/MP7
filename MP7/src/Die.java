/**
 * A 6 sided die that can be rolled. 
 * @author tyler lincheck
 *
 */
public class Die {

	/**
	 * The numerical value of the die.
	 */
	private int value;
	
	/**
	 * Whether the die should be rerolled on the next roll. 
	 */
	private boolean reroll = true;
	
	/**
	 * Creates a new die and rolls it, giving it an int value. 
	 */
	public Die() {
		roll();
	}
	
	/**
	 * Sets value to a random integer from 1 to 6. 
	 */
	public void roll() {
		value = (int) (Math.random() * 6) + 1;
	}
	
	/**
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return reroll
	 */
	public boolean getReRoll() {
		return reroll;
	}
	
	/**
	 * Sets whether the die should be rerolled. 
	 * @param setReRoll
	 */
	public void setReRoll(boolean setReRoll) {
		reroll = setReRoll;
	}
}
