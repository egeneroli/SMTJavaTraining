/**
 * 
 */
package com.evan.training.guessingGame;

//import java.io.Console;
//import java.util.Scanner;
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
public class GuessingGame2 {
	
	/**
	 * Main method -- initializes program, instantiates GuessGame class, calls play (workflow method) 
	 * @param args -- command line args, none necessary
	 */
	public static void main(String[] args) {
		GuessingGame2 game = new GuessingGame2();
		game.play();
	}
	
	/**
	 * Workflow method -- lays out game play logic
	 * -- no arguments necessary
	 */
	public void play() {
	//1-intial game set-up
		//instantiate I/O object 
		//Scanner scan = new Scanner(System.in);
		GameHelper helper = new GameHelper();

		//-prompt user for language choice, take input, store in lang
		//String lang;
		
		//-prompt user for name, take input, store in userName
		//String userName;
		
		//-prompt user for min/max int values for random number, take input, store in minInt/maxInt
		//System.out.print("Enter minimum value for random number generator.");
		//int minInt = scan.nextInt();
		int minInt = helper.getIntInput("Enter minimum integer value for random number generator.");

		//System.out.println("Enter maximum value for random number generator");
		//int maxInt = scan.nextInt();
		int maxInt = helper.getIntInput("Enter maximum integer value for random number generator.");
				
		//-create record variable, initialize (to ?)
		int recordNumOfGuesses = Integer.MAX_VALUE;
		
		// loop for playing game again
		boolean stillPlaying = true;
		while (stillPlaying) {
			//-generate random number, store in secretNumber
			int secretNumber = genRandInt(minInt, maxInt);
			
			//-create numOfGuesses variable, initialize to 0
			int numOfGuesses = 0;
					
		//2-game play
			//repeat until guess is correct
			while (true) {
				//-prompt user for guess, take input, store in userGuess
				//String stringGuess;
				//int userGuess = Integer.parseInt(stringGuess);
				//System.out.println("Guess: enter an integer between " + minInt + " and " + maxInt);
				int userGuess = helper.getIntInput("Enter an integer guess between " + minInt + " and " + maxInt + ".", minInt, maxInt);
				//int userGuess = scan.nextInt();
				
				// increment numOfGuesses
				numOfGuesses++;
			
				//-check userGuess against secretNumber
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
			//-display numOfGuesses
			System.out.println("Correct! The number was " + secretNumber + ".");
			System.out.println("You took " + numOfGuesses + " guesses");
			//-display record numOfGuesses
			if (numOfGuesses < recordNumOfGuesses) {
				recordNumOfGuesses = numOfGuesses;
				System.out.println("You have the new record at " + numOfGuesses + " guesses'");
			} else if (numOfGuesses == recordNumOfGuesses) {
				System.out.println("You tied the record at " + numOfGuesses + " guesses'");
			} else {
				System.out.println("The record is " + recordNumOfGuesses + " guesses.");
			}
			
			//-ask user if they want to play again (yes/no)
			String input = helper.getStringInputYesNo("Play again? (Enter yes/no)");
			if (input.equals("no")) {
				stillPlaying = false;
			}
		}
		
		System.out.println("Game over.");
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
