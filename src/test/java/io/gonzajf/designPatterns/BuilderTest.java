package io.gonzajf.designPatterns;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.gonzajf.designPatterns.creationals.builder.Animal;

public class BuilderTest {

	@Test
	public void builder_test() {
		
		Animal example = new Animal.AnimalBuilder()
							.setName("Name")
							.setAge(5)
							.setFavouriteFoods(Arrays.asList("First", "Second"))
							.build();
		
		Assertions.assertAll(
			() -> Assertions.assertNotNull(example),
			() -> Assertions.assertEquals("Name", example.getName()),
			() -> Assertions.assertEquals(5, example.getAge()),
			() -> Assertions.assertLinesMatch(Arrays.asList("First", "Second"), example.getFavouriteFoods()));
	}
}