package io.gonzajf.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CollectionsTest {
	
	private static List<String> list;
	private static Map<String, String> map;
	
	@BeforeAll
	static void setUp() {
		list = new ArrayList<>(Arrays.asList("java", "python", "c", "scala", "php"));
		
		map = new HashMap<>();
		map.put("language", "java");
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
	
}
