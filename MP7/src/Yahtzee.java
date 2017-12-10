import java.util.Scanner;
public class Yahtzee {
	
	public static void printDice(Die[] rolls) {
		for(int i = 0; i < rolls.length; i++) {
			System.out.print("Dice " + (i+1) + ": " + rolls[i].getValue());
		}
	}

	public static void turn(Scoreboard score) {
		Scanner scanner = new Scanner(System.in);
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
					dice[input].setReRoll(false);
					System.out.print("Dice " + input + " selected to keep.");
				}
				if(input == 6) {
					break;
				}	
			}
			for (int i = 0; i < dice.length; i++) {
				if(dice[i].getReRoll()) {
					dice[i].roll();
				}
			}
			numberOfReRolls--;
		}
		System.out.print("Final rolls for turn: ");
		printDice(dice);
		boolean badInput = true;
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
			int input = scanner.nextInt() - 1;
			badInput = !(score.scoreRound(input, dice));
			score.scoreRound(input, dice);
		}	
	}
}
