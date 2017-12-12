import java.util.Scanner;

/**
 * Runs the yahtzee game. 
 * @author tyler lincheck
 *
 */
public class Yahtzee {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Scoreboard player = new Scoreboard();
		System.out.println("Welcome to Yahtzee.");
		int count = 1;
		while(count <= 13) {
			System.out.println();
			System.out.println("Turn " + count);
			System.out.println();
			turn(player);
			count++;
		}
		System.out.println();
		System.out.println("Game Over!");
		System.out.println("Score = " + player.getScore());
	}
	
	/**
	 * Prints the values of several dice. 
	 * @param rolls an array of dice to print.
	 */
	public static void printDice(Die[] rolls) {
		for(int i = 0; i < rolls.length; i++) {
			System.out.println("Die " + (i+1) + ": " + rolls[i].getValue() + " ");
		}
	}
/**
 * Runs a turn of yahtzee. 
 * @param score the Scoreboard object used to track the player's scores. 
 */
	public static void turn(Scoreboard score) {
		Die[] dice = new Die[5];
		for(int i = 0; i < dice.length; i++) {
			dice[i] = new Die();
		}
		int numberOfReRolls = 2;
		while(numberOfReRolls > 0) {
			printDice(dice);
			while(true) {
				System.out.println("Select dice to keep: 1-5. Press 6 to finish selection.");
				int input = scanner.nextInt();
				if(input > 0 && input < 6) {
					dice[input - 1].setReRoll(false);
					System.out.print("Die " + input + " selected to keep.");
				}
				if(input == 6) {
					break;
				}	
			}
			for (int i = 0; i < dice.length; i++) {
				if(dice[i].getReRoll()) {
					dice[i].roll();
				}
				dice[i].setReRoll(true);
			}
			numberOfReRolls--;
		}
		System.out.println("Final rolls for turn: ");
		printDice(dice);
		boolean badInput = true;
		int input = 0;
		while(badInput) {
			System.out.println();
			System.out.println("Select how you want the round scored: ");
			System.out.println("1-Ones");
			System.out.println("2-Twos");
			System.out.println("3-Threes");
			System.out.println("4-Fours");
			System.out.println("5-Fives");
			System.out.println("6-Sixes");
			System.out.println("7-Three Of A Kind");
			System.out.println("8-Four Of A Kind");
			System.out.println("9-Full House");
			System.out.println("10-Small Straight");
			System.out.println("11-Large Straight");
			System.out.println("12-Chance");
			System.out.println("13-Yahtzee");
			input = scanner.nextInt() - 1;
			badInput = !(score.scoreRound(input, dice));
		}
		System.out.println("Score for turn = " + score.getRoundScore(input));
	}
}
