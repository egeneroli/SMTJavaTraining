/**
 * 
 */
package com.evan.training.guessingGame;

import java.util.Scanner;

/****************************************************************************
* <b>Title</b>: GameHelper.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.guessingGame
* <b>Description: </b> FILL IN DESCRIPTION HERE
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
	
}
