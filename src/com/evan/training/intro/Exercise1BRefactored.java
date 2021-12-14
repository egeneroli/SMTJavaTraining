package com.evan.training.intro;

/**
* <b>Title</b>: Exercise1BRefactored.java
* <b>Project</b>: SMT-Java-Training
* <b>Description: </b> Redo of Exercise 1B -- everything was previously in main method
* 
* @author Evan Generoli
* @version 1.0
* @since Dec 13, 2021
* @updates:
*/



/**
* <b>Title</b>: Exercise1BRefactored.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.intro
* <b>Description: </b> Displays 3 different sequences with loops
* 
* @author egeneroli
* @version 2.0
* @since Dec 13, 2021
* @updates: moves everything out of main method (compared to og Exercise1B)
*/
public class Exercise1BRefactored {
	
	public static void main(String[] args) {
		Exercise1BRefactored ex = new Exercise1BRefactored();
		ex.process();
	}
	
	
	/**
	 * Calls display method with 3 different argument sets to print 3 different sequences
	 */
	public void process() {
		display(1,10,1);
		display(10,1,1);
		display(2,20,2);
	}
	
	
	/**
	 * Prints sequence of numbers from start to end by step
	 * @param start Number to begin sequence
	 * @param end Number to end sequence
	 * @param step Size of increment between each element of sequence
	 */
	public void display(int start, int end, int step) {
		System.out.println("\nNumbers " + start + " to " + end + " by " + step + "s");
		int i = start;
		
		if (start < end) {
			while (i <= end) {
				System.out.println(i);
				i = i + step;
			}
		} else {
			while (i >= end) {
				System.out.println(i);
				i = i - step;
			}
		}
	}
	
}
