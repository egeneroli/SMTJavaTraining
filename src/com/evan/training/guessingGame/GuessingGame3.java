/**
 * 
 */
package com.evan.training.guessingGame;

import java.util.HashMap;
import java.util.Map;

//import java.util.HashMap;
//import java.util.Map;
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
public class GuessingGame3 {
	
	/**
	 * Main method -- initializes program, instantiates GuessGame class, calls play (workflow method) 
	 * @param args -- command line args, none necessary
	 */
	public static void main(String[] args) {
		GuessingGame3 game = new GuessingGame3();
		game.play();
	}
	
	
	/** Constructor
	 * 
	 */
	Map<String, Map<String,String>> stringMap = new HashMap<>();
	public GuessingGame3() {
		
		stringMap.put("languagePrompt", new HashMap<>());
		stringMap.get("languagePrompt").put("english", "Choose language: english/spanish.");
		stringMap.get("languagePrompt").put("spanish", "Elija idioma: inglés / español.");
		
		stringMap.put("namePrompt", new HashMap<>());
		stringMap.get("namePrompt").put("english", "Enter your name.");
		stringMap.get("namePrompt").put("spanish", "Introduzca su nombre.");
		
		stringMap.put("minIntPrompt", new HashMap<>());
		stringMap.get("minIntPrompt").put("english", "Enter minimum integer value for random number generator.");
		stringMap.get("minIntPrompt").put("spanish", "Introduzca el valor entero mínimo para el generador de números aleatorios.");
		
		stringMap.put("maxIntPrompt", new HashMap<>());
		stringMap.get("maxIntPrompt").put("english", "Enter maximum integer value for random number generator.");
		stringMap.get("maxIntPrompt").put("spanish", "Introduzca el valor entero máximo para el generador de números aleatorios.");

		stringMap.put("guessPrompt", new HashMap<>());
		stringMap.get("guessPrompt").put("english", "Enter an integer guess between %d and %d.");
		stringMap.get("guessPrompt").put("spanish", "Ingrese un número entero entre %d y %d.");
		
		stringMap.put("guessTooHigh", new HashMap<>());
		stringMap.get("guessTooHigh").put("english", "Your guess is too low.");
		stringMap.get("guessTooHigh").put("spanish", "Tu conjetura es demasiado baja.");
		
		stringMap.put("guessTooLow", new HashMap<>());
		stringMap.get("guessTooLow").put("english", "Your guess is too high.");
		stringMap.get("guessTooLow").put("spanish", "Tu conjetura es demasiado alta.");

		stringMap.put("guessCorrect", new HashMap<>());
		stringMap.get("guessCorrect").put("english", "Correct! The number was %d.");
		stringMap.get("guessCorrect").put("spanish", "¡Correcto! El numero era %d.");

		stringMap.put("numGuesses", new HashMap<>());
		stringMap.get("numGuesses").put("english", "You took %d guesses.");
		stringMap.get("numGuesses").put("spanish", "Hiciste %d conjeturas.");
		
		stringMap.put("newRecord", new HashMap<>());
		stringMap.get("newRecord").put("english", "You have the new record at %d guesses.");
		stringMap.get("newRecord").put("spanish", "Tienes el nuevo récord en %d suposiciones.");

		stringMap.put("tieRecord", new HashMap<>());
		stringMap.get("tieRecord").put("english", "You tied the record at %d guesses.");
		stringMap.get("tieRecord").put("spanish", "Empataste el récord en %d suposiciones.");

		stringMap.put("notRecord", new HashMap<>());
		stringMap.get("notRecord").put("english", "The record is %d guesses.");
		stringMap.get("notRecord").put("spanish", "El récord es %d suposiciones.");
		
		stringMap.put("playAgain", new HashMap<>());
		stringMap.get("playAgain").put("english", "Play again? (Enter yes/no.)");
		stringMap.get("playAgain").put("spanish", "¿Juega de nuevo? (Ingrese sí / no.)");
		
		stringMap.put("gameOver", new HashMap<>());
		stringMap.get("gameOver").put("english", "Game over.");
		stringMap.get("gameOver").put("spanish", "Juego terminado.");	

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
		String lang = "english";
		
		lang = helper.getStringInputLanguage(stringMap.get("languagePrompt").get(lang));
		//lang = helper.getStringInputLanguage("Choose language: english/spanish.");
		//lang = helper.getStringInputLanguage(helper.langDict("languagePrompt", lang));
		
		//-prompt user for name, take input, store in userName
		String userName = helper.getStringInput(stringMap.get("namePrompt").get(lang));
		//String userName = helper.getStringInput("Enter your name.");
		
		//-prompt user for min/max int values for random number, take input, store in minInt/maxInt
		//System.out.print("Enter minimum value for random number generator.");
		//int minInt = scan.nextInt();
		int minInt = helper.getIntInput(stringMap.get("minIntPrompt").get(lang));
		//int minInt = helper.getIntInput("Enter minimum integer value for random number generator.");

		//System.out.println("Enter maximum value for random number generator");
		//int maxInt = scan.nextInt();
		int maxInt = helper.getIntInput(stringMap.get("maxIntPrompt").get(lang));
		//int maxInt = helper.getIntInput("Enter maximum integer value for random number generator.");
				
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
				//int userGuess = helper.getIntInput("Enter an integer guess between " + minInt + " and " + maxInt + ".", minInt, maxInt);
				int userGuess = helper.getIntInput(String.format(stringMap.get("guessPrompt").get(lang), minInt, maxInt), minInt, maxInt);
				//int userGuess = scan.nextInt();
				
				// increment numOfGuesses
				numOfGuesses++;
			
				//-check userGuess against secretNumber
				if (userGuess == secretNumber) {
					//if correct: proceed to end of game
					break;
					//if incorrect: display "guess too high/low", prompt user for another guess
				} else if (userGuess < secretNumber){
					System.out.println(stringMap.get("guessTooHigh").get(lang));
				} else if (userGuess > secretNumber) {
					System.out.println(stringMap.get("guessTooLow").get(lang));
				}
			}
		//3-end of game
			//-display numOfGuesses
			System.out.println(String.format(stringMap.get("guessCorrect").get(lang), secretNumber));
			System.out.println(String.format(stringMap.get("numGuesses").get(lang), numOfGuesses));
			//-display record numOfGuesses
			if (numOfGuesses < recordNumOfGuesses) {
				recordNumOfGuesses = numOfGuesses;
				System.out.println(String.format(stringMap.get("newRecord").get(lang), numOfGuesses));
			} else if (numOfGuesses == recordNumOfGuesses) {
				System.out.println(String.format(stringMap.get("tieRecord").get(lang), numOfGuesses));
			} else {
				System.out.println(String.format(stringMap.get("notRecord").get(lang), recordNumOfGuesses));
			}
			
			//-ask user if they want to play again (yes/no)
			String input = helper.getStringInputYesNo(stringMap.get("playAgain").get(lang));
			if (input.equals("no")) {
				stillPlaying = false;
			}
		}
		
		System.out.println(stringMap.get("playAgain").get(lang));
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
