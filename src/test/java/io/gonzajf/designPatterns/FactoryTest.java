package io.gonzajf.designPatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.gonzajf.designPatterns.creationals.factory.Fish;
import io.gonzajf.designPatterns.creationals.factory.Food;
import io.gonzajf.designPatterns.creationals.factory.FoodFactory;
import io.gonzajf.designPatterns.creationals.factory.Hay;
import io.gonzajf.designPatterns.creationals.factory.Pellets;

public class FactoryTest {

	@Test
	public void food_consumed_by_zebra() {
		
		Food food = FoodFactory.getFood("zebra");
		Assertions.assertTrue(food instanceof Hay);
		Assertions.assertEquals(100, food.getQuantity());
	}
	
	@Test
	public void food_consumed_by_rabbit() {
		
		Food food = FoodFactory.getFood("rabbit");
		Assertions.assertTrue(food instanceof Pellets);
		Assertions.assertEquals(5, food.getQuantity());
	}
	
	@Test
	public void food_consumed_by_goat() {
		
		Food food = FoodFactory.getFood("goat");
		Assertions.assertTrue(food instanceof Pellets);
		Assertions.assertEquals(30, food.getQuantity());
	}
	
	@Test
	public void food_consumed_by_polar_bear() {
		
		Food food = FoodFactory.getFood("polar bear");
		Assertions.assertTrue(food instanceof Fish);
		Assertions.assertEquals(10, food.getQuantity());
	}
	
	@Test
	public void food_consumed_by_non_existant_animal() {
		
		Assertions.assertThrows(UnsupportedOperationException.class,
				() -> FoodFactory.getFood("unknownr"));
	}
}