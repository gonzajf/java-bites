package io.gonzajf.workingWitInterfaces.primates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.gonzajf.workingWithInterfaces.primates.HasTail;
import io.gonzajf.workingWithInterfaces.primates.Lemur;
import io.gonzajf.workingWithInterfaces.primates.Primate;

public class PrimatesTest {
	
	private static Lemur lemur;
	
	@BeforeAll
	static void setUp() {
		lemur = new Lemur();
	}
	
	@Test
	public void age_is_ten() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Assertions.assertEquals(10, lemur.age);
	}

	@Test
	public void has_tail_striped_is_false() {
		Assertions.assertFalse(lemur.isTailStriped());
	}
	
	@Test
	public void has_hair_is_true() {
		Assertions.assertTrue(lemur.hasHair());
	}
	
	@Test
	public void object_references_test() {
		Assertions.assertTrue(lemur instanceof Lemur);
		Assertions.assertTrue(lemur instanceof Primate);
		Assertions.assertTrue(lemur instanceof HasTail);
	}
	
	@Test
	public void object_vs_reference_test() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		HasTail hasTail = lemur;
		Class<HasTail> tail = HasTail.class;

		//the field does not exist for the reference type HasTail, only exists for
		//the object and the reference type that declares it
		Assertions.assertThrows(NoSuchFieldException.class, () -> {
			tail.getDeclaredField("age").getInt(lemur);
		});
		
		//Although the reference type changed, the object still has access to the
		//the properties declared in his reference types
		Assertions.assertEquals(10, hasTail.getClass().getDeclaredField("age").getInt(hasTail));
	}
}