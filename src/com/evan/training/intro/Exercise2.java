package com.evan.training.intro;

public class Exercise2 {
	public static void main(String[] args) {
		
		// print numbers 1-10 with while loop
		int i = 0; // declare int variable called i, initialize with value of 0
		while (i <= 10) {
			System.out.println(i);
			i++;
		}
		
		// print numbers 10-1 with for loop
		for(i=10;i>0;i--) {
			System.out.print(i);
		}
		
		// print even numbers 1-20 with for loop
		for(i=1;i<=20;i=i+2) {
			System.out.print(i);
		}
		
		
	}
}
