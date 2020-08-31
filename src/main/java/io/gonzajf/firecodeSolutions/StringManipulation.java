package io.gonzajf.firecodeSolutions;

import java.util.HashMap;
import java.util.Map;

public class StringManipulation {
	
	
	/**
	 *  Write a method to replace all spaces in a string with a given replacement string.
	 *  replace("This is a test","/") --> "This/is/a/test"
	 *  Note: Avoid using the in-built String.replaceAll() method. 
	 */
	public static String replace(String a, String b) {
		
		StringBuilder answer = new StringBuilder();
				
		for (int i = 0; i < a.length(); i++) {
			
			if(a.charAt(i) == ' ') {
				answer.append(b);
			} else {
				answer.append(a.charAt(i));
			}
		}
		return answer.toString();
	}
	
	/**
	 * Write a method that takes in a String and returns the reversed version of the String.
	 */
	public static String reverseString(String str){
		if(str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = str.length()-1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	/**
	 * Write a method that takes in an input String and returns true if all the characters
	 *  in the String are unique and false if there is even a single repeated character.
	 *  The method should return true if the input is null or empty String.
	 */
	public static boolean areAllCharactersUnique(String str){
		
		if(str == null || str.isEmpty()) {
			return true;
		}
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < str.length(); i++) {
			if(map.containsKey(str.charAt(i))) {
				return false;
			}
			map.put(str.charAt(i), 1);
		}
		return true;
	}  

	public static void main(String[] args) {
		System.out.println(areAllCharactersUnique(null));
	}

}
