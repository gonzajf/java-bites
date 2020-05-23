package io.gonzajf.functionalProgramming;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

/**
 * Predicate is often used when filtering or matching.
 * A BiPredicate is just like a Predicate except that it takes two parameters instead of one.
 */
public class PredicateTest {

	@Test
	public void predicate_test() {
		
		Predicate<String> p1 = String::isEmpty;
		
		assertAll(
			() -> assertTrue(p1.test("")),
			() -> assertFalse(p1.test("java")));
	}
	
	@Test
	public void bi_predicate_test() {
		
		BiPredicate<String, String> b1 = String::startsWith;
	
		assertAll(
			() -> assertTrue(b1.test("javascript", "java")),
			() -> assertFalse(b1.test("", "java")));
	}
}