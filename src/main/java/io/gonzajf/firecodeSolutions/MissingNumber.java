package io.gonzajf.firecodeSolutions;

public class MissingNumber {
	
	/**
	 * Given an Array containing 9 numbers ranging from 1 to 10, 
	 * write a method to find the missing number. 
	 * Assume you have 9 numbers between 1 to 10 and only one number is missing.
	 */

	public static int findMissingNumber(int[] arr) {
		
		int total = 55;//sumatory from 1 to 10
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return total - sum;
	}
}