package io.gonzajf.nio2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PathTest {

	private static Path path;
	
	@BeforeAll
	static void setUp() {
		path = Paths.get("src/test/resources/exampleFile.txt");
	}
	
	@Test
	public void path_creation_test() {
		Path path2 = Paths.get("src", "test", "resources", "exampleFile.txt");
		assertEquals(path, path2);
	}
	
	@Test
	public void path_name_count_test() {
		assertEquals(4, path.getNameCount());
	}
	
	@Test
	public void get_file_name_path_test() {
		assertTrue(path.getFileName() instanceof Path);
		assertEquals("exampleFile.txt", path.getFileName().toString());
	}
	
	@Test
	public void get_parent_path_test() {
		assertTrue(path.getParent() instanceof Path);
		assertEquals("src/test/resources", path.getParent().toString());
	}
	
	//is null because it's a relative path
	@Test
	public void get_root_path() {
		assertEquals(null, path.getRoot());
	}
	
	@Test
	public void test_relative() {
		assertFalse(path.isAbsolute());
	}
	
	@Test
	public void convert_to_absoluth_path_test() {
		assertTrue(path.toAbsolutePath().isAbsolute());
	}
}
