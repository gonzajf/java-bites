package io.gonzajf.firecodeSolutions;

public class StringReplace {
	
	
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
}
