package com.evan.training.intro;

public class Exercise2 {
	public static void main(String[] args) {
		
		// print numbers 1-10 with while loop
		System.out.println(); // print blank line
		System.out.println("Numbers 1-10 w/ while loop");
		int i = 1; // declare int variable called i, initialize with value of 0
		while (i <= 10) {
			System.out.println(i);
			i++;
		}
		
		// print numbers 10-1 with for loop
		System.out.println(); // print blank line
		System.out.println("Numbers 10-1 w/ for loop");
		for(int j=10;j>0;j--) {
			System.out.println(j);
		}
		
		
		// print even numbers 1-20 with for loop
		System.out.println(); // print blank line
		System.out.println("Even numbers 1-20 w/ for loop");
		for(int k=2;k<=20;k=k+2) {
			System.out.println(k);
		}
		
		
	}
}
