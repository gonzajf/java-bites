package io.gonzajf.functionalProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A Consumer is used when you want to do something with a parameter but not
 * returning anything. BiConsumer does the same thing except that takes two
 * parameters.
 */
public class ConsumersTest {

	@Test
	public void consumer_test() {

		List<String> list = new ArrayList<>();
	
		Consumer<String> c1 = list::add;
		c1.accept("java");
		c1.accept("python");
		
		Assertions.assertAll(
				() -> Assertions.assertEquals(2, list.size()),
				() -> Assertions.assertEquals("[java, python]", list.toString()));
	}
	
	@Test
	public void bi_consumer_test() {
		
		Map<String, String> map = new HashMap<>();
		
		BiConsumer<String, String> b1 = map::put;
		b1.accept("language", "java");
		
		Assertions.assertAll(
				() -> Assertions.assertEquals(1, map.size()),
				() -> Assertions.assertTrue(map.containsKey("language")));
	}
}
