package io.gonzajf.functionalProgramming;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * UnaryOperator and BinaryOperator are a special case of a function. They require all type
 * parameters to be the same type. 
 * A UnaryOperator transforms its value into one of the same type.
 * A BinaryOperator merges two values into one of the same type.
 */
public class OperatorTest {

	@Test
	public void unary_operator_test() {
		
		UnaryOperator<String> u1 = String::toUpperCase;
		Assertions.assertEquals("JAVA", u1.apply("java"));
	}
	
	@Test
	public void binary_operator_test() {
		
		BinaryOperator<BigDecimal> b1 = BigDecimal::add;
		Assertions.assertEquals(BigDecimal.valueOf(30), b1.apply(BigDecimal.valueOf(10), BigDecimal.valueOf(20)));
	}
}