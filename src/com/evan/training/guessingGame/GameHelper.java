/**
 * 
 */
package com.evan.training.guessingGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/****************************************************************************
* <b>Title</b>: GameHelper.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.guessingGame
* <b>Description: </b> Assists guessing game with I/O
* 
* @author egeneroli
* @version 1.0
* @since Dec 20, 2021
* @updates:
****************************************************************************/
public class GameHelper {
	Scanner scan = new Scanner(System.in);
	
	
	
	/**
	 * takes string input from user
	 * @param prompt Prints to user as prompt for input -- enter empty string if none
	 * @return String of user input
	 */
	public String getStringInput(String prompt) {
		//Scanner scan = new Scanner(System.in);
		String input = "";
		while (true) {
			System.out.println(prompt);
			input = scan.next();
			if (input.length() > 0 && input.matches("[A-Za-z]+")) {
				//scan.close();
				break;
			}
		}
		return input.toLowerCase();

	}	
	
	/**
	 * takes integer input from user
	 * @param prompt Prints to user as prompt for input -- enter empty string if none
	 * @return Int -- user input
	 */
	public int getIntInput(String prompt) {
		//Scanner scan = new Scanner(System.in); 
		String input = "";
		while (true) {
			System.out.println(prompt);
			input = scan.next();
			if (input.length() > 0 && input.matches("[0-9]+")) {
				//scan.close();
				break;
			}
		}
		return Integer.parseInt(input);
	}	

	/**
	 * takes integer input from user
	 * @param prompt Prints to user as prompt for input -- enter empty string if none
	 * @param min Minimum number that will be accepted
	 * @param max Maximum number that will be accepted
	 * @return Int -- user input
	 */
	public int getIntInput(String prompt, int min, int max) {
		//Scanner scan = new Scanner(System.in); 
		String input = "";
		while (true) {
			System.out.println(prompt);
			input = scan.next();
			if (input.length() > 0 && input.matches("[0-9]+") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max) {
				//scan.close();
				break;
			}
		}
		return Integer.parseInt(input);
	}	
	
	/**
	 * takes string input yes/no from user
	 * @param prompt Prints to user as prompt for input -- enter empty string if none
	 * @return String of user input (yes or no)
	 */
	public String getStringInputYesNo(String prompt) {
		//Scanner scan = new Scanner(System.in);
		String input = "";
		while (true) {
			System.out.println(prompt);
			input = scan.next();
			if (input.length() > 0 && input.toLowerCase().matches("(yes|no|si)")) {
				//scan.close();
				break;
			}
		}
		return input.toLowerCase();

	}	
	
	/**
	 * takes string input yes/no from user
	 * @param prompt Prints to user as prompt for input -- enter empty string if none
	 * @return String of user input (yes or no)
	 */
	public String getStringInputLanguage(String prompt) {
		//Scanner scan = new Scanner(System.in);
		String input = "";
		while (true) {
			System.out.println(prompt);
			input = scan.next();
			if (input.length() > 0 && input.toLowerCase().matches("(english|spanish)")) {
				//scan.close();
				break;
			}
		}
		return input.toLowerCase();

	}	
	
	/**
	 * gives proper string for output by topic & language
	 * @param topic Topic to get, ie. languagePrompt
	 * @param language English/Spanish
	 * @return String 
	 */
	public String langDict(String topic, String language) {
		Map<String, Map<String,String>> stringMap = new HashMap<>();
		
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
		
		return stringMap.get(topic).get(language);
		
	}
}
