package io.gonzajf.collections;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparatorTest {
	
	static class Rabbit{ int id; }

	//test fails because TreeSet is an ordered collection (based on natural order) 
	//and doesn't know how to compare elements when the insertion occurs
	@Test
	public void adding_element_fails() {
		Set<Rabbit> rabbits = new TreeSet<>();
		Assertions.assertThrows(ClassCastException.class, 
				() -> rabbits.add(new Rabbit()));
	}
	
	@Test
	public void adding_element_with_comparator() {
		
		Comparator<Rabbit> byId = (r1, r2) -> r1.id - r2.id;
		Set<Rabbit> rabbits = new TreeSet<>(byId);
		rabbits.add(new Rabbit());
		Assertions.assertEquals(1, rabbits.size());
	}
}