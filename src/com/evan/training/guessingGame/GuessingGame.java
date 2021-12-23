/**
 * 
 */
package com.evan.training.guessingGame;
import java.util.Scanner;
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
	 * Workflow method -- lays out game play logic
	 * -- no arguments necessary
	 */
	public void play() {
	//1-intial game set-up
		//instantiate object of scanner class to get user input
		Scanner scan = new Scanner(System.in);
		//GameHelper helper = new GameHelper();
		
		//a-prompt user for language choice, take input, store in lang
		//String lang;
		
		//b-prompt user for min/max int values for random number, take input, store in minInt/maxInt
		System.out.print("Enter minimum value for random number generator.");
		//String input = helper.getUserInput("Enter minimum value for random number generator.");
		
		int minInt = scan.nextInt();
		//String input = scan.next();
		//int i = Integer.parseInt(scan.next());

		System.out.println("Enter maximum value for random number generator");
		int maxInt = scan.nextInt();
		
		//c-prompt user for name, take input, store in userName
		//String userName;
		
		//d-generate random number, store in secretNumber
		int secretNumber = genRandInt(minInt, maxInt);
		
		//e-create numOfGuesses variable, initialize to 0
		int numOfGuesses = 0;
		
		//f-create record variable, initialize (to ?)
		//int recordNumOfGuesses;
		
	//2-game play
		//repeat until guess is correct
		while (true) {
			//a-prompt user for guess, take input, store in userGuess
			//String stringGuess;
			//int userGuess = Integer.parseInt(stringGuess);
			System.out.println("Guess: enter a number between " + minInt + " and " + maxInt);
			int userGuess = scan.nextInt();
			
			// increment numOfGuesses
			numOfGuesses++;
		
			//b-check userGuess against secretNumber
			if (userGuess == secretNumber) {
				//if correct: proceed to end of game
				break;
				//if incorrect: display "guess too high/low", prompt user for another guess
			} else if (userGuess < secretNumber){
				System.out.println("Guess is too low.");
			} else if (userGuess > secretNumber) {
				System.out.println("Guess is too high");
			}
		}
	//3-end of game
		//a-display numOfGuesses
		System.out.println("Correct! The number was " + secretNumber + ".");
		System.out.println("You took " + numOfGuesses + " guesses");
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
