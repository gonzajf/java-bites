package io.gonzajf.designPatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.gonzajf.designPatterns.creationals.Singleton;

public class SingletonTest {

	private static Singleton instance1;
	private static Singleton instance2;
	
	@BeforeAll
	public static void setUp() {
		instance1 = Singleton.getInstance();
		instance2 = Singleton.getInstance();
	}
	
	@Test
	public void instances_are_same_object() {
		Assertions.assertSame(instance1, instance2);
	}
}