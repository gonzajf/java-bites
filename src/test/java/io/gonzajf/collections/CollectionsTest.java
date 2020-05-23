package io.gonzajf.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectionsTest {
	
	private static List<String> list;
	private static Map<String, String> map;
	private static Map<String, Integer> counts;
	
	@BeforeEach
	public void setUp() {
		list = new ArrayList<>(Arrays.asList("java", "python", "c", "scala", "php"));
		
		map = new HashMap<>();
		map.put("language", "java");
		
		counts = new HashMap<>();
		counts.put("A", 1);
	}
	
	@Test
	public void remove_if_test() {
		boolean removed = list.removeIf(s-> s.startsWith("p"));
		Assertions.assertTrue(removed);
		Assertions.assertEquals(3, list.size());
	}
	
	@Test
	public void replace_all_test() {
		list.replaceAll(s-> s.concat("-dev"));
		Assertions.assertTrue(list.stream().allMatch(s -> s.endsWith("-dev")));
	}

	@Test
	public void put_if_absent_test() {
		
		map.putIfAbsent("IDE", "eclipse");
		
		//if key value is already present does nothing
		String previuosIde = map.putIfAbsent("IDE", "intelliJ");
		
		//if key value is already present, replaces value
		String previousLanguage = map.put("language", "scala");
	
		Assertions.assertEquals("java", previousLanguage);
		Assertions.assertEquals("scala", map.get("language"));
		
		Assertions.assertEquals("eclipse", previuosIde);
		Assertions.assertEquals("eclipse", map.get("IDE"));
		
		Assertions.assertEquals(2, map.size());
	}

	@Test
	public void merge_test() {
		
		BiFunction<String, String, String> mapper = (String::concat);
				
		String javaSpring = map.merge("language", "-spring", mapper);
		Assertions.assertEquals("java-spring", javaSpring);
	}
	
	@Test
	public void compute_if_present() {

		Integer a = counts.computeIfPresent("A", (k, v) -> v + 1);
		Integer b = counts.computeIfPresent("B", (k, v) -> v + 1);

		Assertions.assertEquals(2, a);
		Assertions.assertNull(b);
	}
	
	@Test
	public void compute_if_absent() {

		counts = new HashMap<>();
		counts.put("A", 1);

		Integer a = counts.computeIfAbsent("A", k -> 1);
		Integer b = counts.computeIfAbsent("B", k -> 1);

		Assertions.assertEquals(a, counts.get("A"));
		Assertions.assertEquals(b, counts.get("B"));
	}
}