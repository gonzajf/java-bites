package io.gonzajf.functionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A Function is responsible for turning one parameter into a value of a potentially different
 * type and returning it. 
 * BiFunction is responsible for turning two parameters into a value and returning it.
 */
public class FunctionsTest {

	@DisplayName("converts a string to the length of the string")
	@Test
	public void function_test() {
		
		Function<String, Integer> f1 = String::length;
		Assertions.assertEquals(4, f1.apply("java"));
	}
	
	@DisplayName("Returns a string product of concatenation of first two string parameters")
	@Test
	public void bi_function_test() {
		
		BiFunction<String, String, String> b1 = String::concat;
		Assertions.assertEquals("javascript", b1.apply("java", "script"));
	}
}