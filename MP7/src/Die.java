
public class Die {

	private int value;
	
	private boolean reroll = true;
	
	public void roll() {
		value = (int) Math.random() * 6 + 1;
	}
}
