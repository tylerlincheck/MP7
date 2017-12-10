
public class Die {

	private int value;
	
	private boolean reroll = true;
	
	public Die() {
		roll();
	}
	
	public void roll() {
		value = (int) Math.random() * 6 + 1;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean getReRoll() {
		return reroll;
	}
	
	public void setReRoll(boolean setReRoll) {
		reroll = setReRoll;
	}
}
