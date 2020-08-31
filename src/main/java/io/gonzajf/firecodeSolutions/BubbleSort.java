package io.gonzajf.firecodeSolutions;

public class BubbleSort {

	/**
	 * Write a method that takes in an array of ints and uses the Bubble Sort algorithm 
	 * to sort the array 'in place' in ascending order. 
	 *
	 * The method should return the same, in-place sorted array.
	 */
	public static int[] bubbleSortArray(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length-i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
}
