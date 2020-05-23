package io.gonzajf.functionalProgramming;

import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A supplier is used when you want to generate or supply values without taking any input
 */
public class SupplierTest {
	
	
	@Test
	public void creating_suppliers_test() {
		
		Supplier<StringBuilder> s1 = StringBuilder::new;
		Supplier<StringBuilder> s2 = () -> new StringBuilder();
		
		Assertions.assertAll(
				() -> Assertions.assertNotSame(s1, s2), 
				() -> Assertions.assertTrue(s1 instanceof Supplier),
				() -> Assertions.assertTrue(s1.get() instanceof StringBuilder));
	}
}