/**
 * 
 */
package com.evan.training.intro;

/**
* <b>Title</b>: Exercise2.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.intro
* <b>Description: </b> Creates 10 element array of random numbers and displays it
* 
* @author egeneroli
* @version 1.0
* @since Dec 13, 2021
* @updates:
*/

public class Exercise2 {

	public static void main(String[] args) {
		
		Exercise2 ex = new Exercise2();
		ex.process(15, 0, 10);	
		
	}
	
	/**
	 * Creates, initializes int array, fills with random numbers and prints
	 * @param arrayLength 
	 */
	public void process(int arrayLength, int minInt, int maxInt) {
		int[] myArray = new int[arrayLength];
		
		System.out.println("Row: Value");
		for(int i=0; i < myArray.length; i++) {
			myArray[i] = genRandInt(minInt, maxInt);
			System.out.println(i + ":   " + myArray[i]);
		}
	}
	
	/**
	 * Generates random integer in specified range (min, max)
	 * @param min Minimum number of random integer generator range
	 * @param max Maximum number of random integer generator range
	 * @return Returns integer
	 */
	public int genRandInt(int min, int max) {
		int randVal = min + (int) (Math.random() * ((max - min) + 1));
		return randVal;
	}
	
}
