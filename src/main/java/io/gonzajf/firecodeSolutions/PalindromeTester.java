package io.gonzajf.firecodeSolutions;

public class PalindromeTester {

	/**
	 * A palindrome is a string or sequence of characters that reads the same backward as forward. 
	 * For example, "madam" is a palindrome. 
	 * 
	 * Write a method that takes in a String and returns a boolean -> true if the input String is a 
	 * palindrome and false if it is not. 
	 *
	 * An empty string and a null input are considered palindromes. 
	 * 
	 * You also need to account for the space character. 
	 * 
	 * For example, "race car" should return false as read backward it is "rac ecar".
	 * 
	 */
	public static boolean isStringPalindrome(String str) {

		if(str == null) {
			return true;
		}
		char[] array = str.toCharArray();
		int j = str.length()-1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] != array[j]) {
				return false;
			}
			j--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isStringPalindrome(null));
	}
}
