package io.gonzajf.firecodeSolutions;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class DuplicatesInArray {

	/**
	 * Write a method duplicate to find the repeated or duplicate elements in an
	 * array. This method should return a list of repeated integers in a string with
	 * the elements sorted in ascending order
	 */
	public static String duplicate(int[] numbers) {

		Arrays.sort(numbers);
		Set<Integer> response = new TreeSet<>();
		
		for (int i = 1; i < numbers.length; i++) {
			if(numbers[i-1] == numbers[i]) {
				response.add(numbers[i]);
			}
		}
		return response.toString();
	}
}
