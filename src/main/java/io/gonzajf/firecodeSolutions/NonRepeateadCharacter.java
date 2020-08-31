package io.gonzajf.firecodeSolutions;

import java.util.HashMap;
import java.util.Map;

public class NonRepeateadCharacter {
	
	public static Character firstNonRepeatedCharacter(String str) {
		
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < str.length(); i++) {
			map.merge(str.charAt(i), 1, Integer::sum);
		}
		
		for(Character c: map.keySet()) {
			if(map.get(c) == 1) {
				return c;
			}
		}
		return null;
	}
}
