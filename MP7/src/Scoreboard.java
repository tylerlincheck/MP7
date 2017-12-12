/**
 * Scoreboard of 13 rounds for the yahtzee game
 * @author jarod meyer
 *
 */
public class Scoreboard {
	/**
	 * Make an array of 13 (number of rounds AND different scoretypes)
	 */
	private int[] roundScoring = new int[13];
	
	/**
	 * Initialize a scoreboard to all rounds/scoring types unused
	 */
	public Scoreboard() {
		//initialize to -1 to indicate unused scoring
		for (int index = 0; index < 13; index++) {
			roundScoring[index] = -1;
		}
	}
	
	/**
	 * 
	 * @return The added scores from roundScoring, with a possible bonus. 
	 */
	public int getScore() {
		int score = 0;
		for(int i = 0; i < 6; i++) {
			score += roundScoring[i];
		}
		if(score > 62) {
			score += 35;
		}
		for(int i = 6; i < 13; i++) {
			score += roundScoring[i];
		}
		return score;
	}
	
	/**
	 * @return The score for a specific index.
	 */
	
	public int getRoundScore(int index) {
		return roundScoring[index];
	}
	
	/**
	 * Sorts the die roll vals in ascending order, helper for scoring methods.
	 * @param rolls the array of Die
	 * @return an int[] array (sorted) of the rolls
	 */
	public static int[] sortDieVals(Die[] rolls) {
		int[] arr = new int[rolls.length];
		//fill array with roll values
		for (int x = 0; x < arr.length; x++) {
			arr[x] = rolls[x].getValue();
		}
		//selection sort that baby
		int currentSpot = 0;
		while (currentSpot < arr.length - 1) {
			int indexMin = currentSpot;
			for (int selecIndex = currentSpot + 1; selecIndex < arr.length; selecIndex++) {
				if (arr[selecIndex] < arr[indexMin]) {
					indexMin = selecIndex;
				}
			}
			//now swap
			int temp = arr[currentSpot];
			arr[currentSpot] = arr[indexMin];
			arr[indexMin] = temp;
			currentSpot++;
		}
		return arr;
	}
	
	/**
	 * A method for scoring a round.
	 * @param index the type of scoring to use
	 * @param rolls an array of the five rolls to use
	 * @return true IF that round was unused, and the size of rolls was valid
	 * return false if that index/scoring type was used already, or if rolls size != 5
	 */
	public boolean scoreRound(int index, Die[] rolls) {
		//invalid roll size
		if (rolls.length != 5) {
			System.out.println("Invalid array size!");
			return false;
		} else if (roundScoring[index] != -1) {
			//if a scoring type was used already, then invalid!
			System.out.println("Invalid: Scoring type already used!");
			return false;
		} else {
			//sort dievals in case needed for certain cases:
			int[] valRollsSorted = sortDieVals(rolls);
			//the different types of scoring for the dif rounds
			switch(index) {
				//ones
				case 0:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						if (aDie.getValue() == 1) {
							roundScoring[index] += 1;
						}
					}
					break;
				//twos
				case 1:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						if (aDie.getValue() == 2) {
							roundScoring[index] += 2;
						}
					}
					break;
				//threes
				case 2:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						if (aDie.getValue() == 3) {
							roundScoring[index] += 3;
						}
					}
					break;
				//fours
				case 3:
					roundScoring[index] = 0;
					for (Die aDie : rolls ) {
						if (aDie.getValue() == 4) {
							roundScoring[index] += 4;
						}
					}
					break;
				//fives
				case 4:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						if (aDie.getValue() == 5) {
							roundScoring[index] += 5;
						}
					}
					break;
				//sixes
				case 5:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						if (aDie.getValue() == 6) {
							roundScoring[index] += 6;
						}
					}
					break;
				//three of a kind
				case 6:
					roundScoring[index] = 0;
					//count how many of a num get repeated
					int count = 0;
					for (int i = 0; i < valRollsSorted.length; i++) {
						int numOfAKind = 1;
						for (int j = i+1; j < valRollsSorted.length; j++) {
							if (valRollsSorted[j] == valRollsSorted[i]) {
								numOfAKind++;
							}
						}
						for (int k = i - 1; k >= 0; k--) {
							if (valRollsSorted[k] == valRollsSorted[i]) {
								numOfAKind++;
							}
						}
						//now set equal to max count
						if (numOfAKind > count) {
							count = numOfAKind;
						}
					}
					if (count >= 3) {
						for (Die aDie : rolls) {
							roundScoring[index] += aDie.getValue();
						}
					}
					break;
				//four of a kind
				case 7:
					roundScoring[index] = 0;
					//count how many of a num get repeated
					int count4 = 0;
					for (int i = 0; i < valRollsSorted.length; i++) {
						int numOfAKind = 1;
						for (int j = i+1; j < valRollsSorted.length; j++) {
							if (valRollsSorted[j] == valRollsSorted[i]) {
								numOfAKind++;
							}
						}
						for (int k = i - 1; k >= 0; k--) {
							if (valRollsSorted[k] == valRollsSorted[i]) {
								numOfAKind++;
							}
						}
						//now set equal to max count
						if (numOfAKind > count4) {
							count4 = numOfAKind;
						}
					}
					//four of a kind
					if (count4 >= 4) {
						for (Die aDie : rolls) {
							roundScoring[index] += aDie.getValue();
						}
					}
					break;
				//full house (three of a kind and then a pair)
				//best way to do this is to have sorted rolls data first
				case 8:
					roundScoring[index] = 0;
					//if the vals are sorted, then if there is a full house:
					//the first three vals are 3 of a kind or the last three vals are
					//the first two vals are a pair or the last two are
					boolean fullHouse = true;
					if (valRollsSorted[0] == valRollsSorted[1]) {
						//three of a kind
						if (valRollsSorted[0] == valRollsSorted[2]) {
							//no pair for the next two
							if (valRollsSorted[3] != valRollsSorted[4]) {
								fullHouse = false;
							} 
						} else {
							//check if last three are three of a kind. Dont need to check all
							if (valRollsSorted[2] != valRollsSorted[3]) {
								fullHouse = false;
							} else {
								if (valRollsSorted[2] != valRollsSorted[4]) {
									fullHouse = false;
								}
							}
						}
					} else {
						fullHouse = false;
					}
					
					if(fullHouse) {
						roundScoring[index] += 25;
					}
					break;
				//small straight (four consec)
				//have sorted rolls data first
				case 9:
					roundScoring[index] = 0;
					boolean consecStartFirst = true;
					boolean consecStartSecond = true;
					for (int straightInd = 1; straightInd < rolls.length - 1; straightInd++) {
						if (valRollsSorted[straightInd - 1] != valRollsSorted[straightInd] - 1) {
							consecStartFirst = false;
							break;
						}
					}
					if (!consecStartFirst) {
						for (int straightInd2 = 2; straightInd2 < rolls.length - 1; straightInd2++) {
							if (valRollsSorted[straightInd2 - 1] != valRollsSorted[straightInd2] - 1) {
								consecStartSecond = false;
								break;
							}
						}
					}
					if (consecStartFirst || consecStartSecond) {
						roundScoring[index] += 30;
					}
					break;
				//large straight (all five consec)
				//have sorted rolls data first
				case 10:
					roundScoring[index] = 0;
					boolean consec = true;
					for (int largeInd = 1; largeInd < valRollsSorted.length; largeInd++) {
						if (valRollsSorted[largeInd - 1] != valRollsSorted[largeInd] - 1) {
							consec = false;
							break;
						}
					}
					if (consec) {
						roundScoring[index] += 40;
					}
					break;
				//chance
				case 11:
					roundScoring[index] = 0;
					for (Die aDie : rolls) {
						roundScoring[index] += aDie.getValue();
					}
					break;
				//yahtzee
				case 12:
					roundScoring[index] = 0;
					boolean yahtzee = true;
					int num = rolls[0].getValue();
					for (int yahtzeeInd = 1; yahtzeeInd < rolls.length; yahtzeeInd++) {
						if (rolls[yahtzeeInd].getValue() != num) {
							yahtzee = false;
							break;
						}
					}
					if (yahtzee) {
						roundScoring[index] += 50;
					}
					
					break;
				//index not in score array (0-12 only)
				default:
					break;
					
			}
			return true;
		}
	}
	
}