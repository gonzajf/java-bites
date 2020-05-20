package io.gonzajf.workingWitInterfaces.birds;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.gonzajf.workingWithInterfaces.birds.Birdie;
import io.gonzajf.workingWithInterfaces.birds.Eagle;
import io.gonzajf.workingWithInterfaces.birds.Fly;

public class InterfacesTest {
	
	@Test
	public void interface_has_default_method() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		
		Class<Fly> flyInterface = Fly.class;
		int value = flyInterface.getDeclaredField("MAX_SPEED").getInt(null);
		Method m = flyInterface.getMethod("land", (Class<?>[]) null);
		Assertions.assertTrue(m.isDefault());
		Assertions.assertEquals(100, value);
	}
	
	@Test
	public void implementation_override_default_method() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
		Class<Eagle> eagleImplementation = Eagle.class;
		Method m = eagleImplementation.getMethod("land", (Class<?>[]) null);
		Assertions.assertFalse(m.isDefault());
		Assertions.assertThrows(NoSuchFieldException.class, () -> {
			eagleImplementation.getDeclaredField("MAX_SPEED").getInt(null);
		});
	}

	@Test
	public void implementation_not_override_default_method() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
		Class<Birdie> birdieImplementation = Birdie.class;
		Method m = birdieImplementation.getMethod("land", (Class<?>[]) null);
		Assertions.assertTrue(m.isDefault());
		Assertions.assertThrows(NoSuchFieldException.class, () -> {
			birdieImplementation.getDeclaredField("MAX_SPEED").getInt(null);
		});
	}
}