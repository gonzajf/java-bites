package io.gonzajf.firecodeSolutions;

import java.util.HashMap;

public class SingleNumber {
	
	/**
	 * Write a method that returns a number that appears only once in an array. 
	 * Assume the array will surely have a unique value. The array will never be empty.
	 * Examples: {1,2,3,4,1,2,4,3,5} ==> 5
	 */
	public static int singleNumber(int[] A) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			map.merge(A[i], 1, Integer::sum);
		}
		
		for(Integer k: map.keySet()) {
            if(map.get(k) == 1) {
                return k;
            }
		}
		return -1;
	}
}
