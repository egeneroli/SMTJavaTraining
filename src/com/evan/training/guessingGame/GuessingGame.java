/**
 * 
 */
package com.evan.training.guessingGame;

/****************************************************************************
* <b>Title</b>: GuessingGame.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.guessingGame
* <b>Description: </b> Runs guessing game
* 
* @author egeneroli
* @version 1.0
* @since Dec 15, 2021
* @updates:
****************************************************************************/
public class GuessingGame {
	
	/**
	 * Main method -- initializes program, instantiates GuessGame class, calls play (workflow method) 
	 * @param args -- command line args, none necessary
	 */
	public static void main(String[] args) {
		GuessingGame game = new GuessingGame();
		game.play();
	}
	
	/**
	 * Workflow method -- lays out logic of game 
	 */
	public void play() {
	//1-intial game set-up
		//a-prompt user for language choice, take input, store in lang
		int language;
		
		//b-prompt user for min/max int values for random number, take input, store in minInt/maxInt
		int minInt;
		int maxInt;
		
		//c-prompt user for name, take input, store in userName
		int userName;
		
		//d-generate random number, store in secretNumber
		int secretNumber = genRandInt(minInt, maxInt);
		
		//e-create numOfGuesses variable, initialize to 0
		int numOfGuesses = 0;
		
		//f-create record variable, initialize to ?
		int recordNumOfGuesses;
		
	//2-game play
		//repeat until guess is correct
		while (true) {
			//a-prompt user for guess, take input, store in userGuess
			String userGuess;
			int userGuess = Integer.parseInt(userGuess);
			
			// increment numOfGuesses
			numOfGuesses++;
		
			//b-check userGuess against secretNumber
			if (userGuess != secretNumber) {
				//if incorrect: display "guess too high/low", prompt user for another guess
				if (userGuess > secretNumber) {
					System.out.println("Guess is too high. Guess again.");
				} else {
					System.out.println("Guess is too low. Guess again.");
				}
				
			} else {
				//if correct: proceed to end of game
				System.out.println("Correct, the number was " + secretNumber + ".");
				break;
			}
		}
	//3-end of game
		//a-display numOfGuesses
		
		//b-display record numOfGuesses
		
		//c-prompt user if they want to play again (yes/no)
			//if yes: return to initial game set-up
		
			//if no: end, display game over
	}
	
	
	/**
	 * Generates random integer in range [min,max]
	 * @param min
	 * @param max
	 * @return int -- random integer
	 */
	public int genRandInt(int min, int max) {
		int randVal = min + (int) (Math.random() * ((max - min) + 1));
		return randVal;
	}
	
	
}
