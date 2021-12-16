/**
 * 
 */
package com.evan.training.guessingGame;

/****************************************************************************
* <b>Title</b>: GuessingGameOutline.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.guessingGame
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Dec 16, 2021
* @updates:
****************************************************************************/
public class GuessingGameOutline {
	
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

		//b-prompt user for min/max int values for random number, take input, store in minInt/maxInt
		
		//c-prompt user for name, take input, store in userName
		
		//d-generate random number, store in secretNumber
		
		//e-create numOfGuesses variable, initialize to 0
		
		//f-create record variable, initialize (to ?)
		
	//2-game play
		//repeat until guess is correct

			//a-prompt user for guess, take input, store in userGuess

			// increment numOfGuesses

			//b-check userGuess against secretNumber
				//if incorrect: display "guess too high/low", prompt user for another guess

				//if correct: proceed to end of game

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
	 * @return int 
	 */
	public int genRandInt(int min, int max) {
		int randVal = min + (int) (Math.random() * ((max - min) + 1));
		return randVal;
	}
}
