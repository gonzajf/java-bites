package io.gonzajf.nio2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

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
	
	@Test
	public void getting_subpath_test() {
		assertAll(
			() -> assertEquals("src/test", path.subpath(0, 2).toString()),
			() -> assertEquals("test", path.subpath(1, 2).toString()),
			() -> assertThrows(IllegalArgumentException.class, () -> path.subpath(0,5)));
	}
	
	@Test
	public void file_exists_test() {
		assertTrue(Files.exists(path));
	}
	
	@Test
	public void same_file_test() throws IOException {
		Path path2 = Paths.get("src", "test", "resources", "exampleFile.txt");
		assertTrue(Files.isSameFile(path, path2));
	}
	
	@Test
	public void attributes_test() throws IOException {
		assertAll(
			() -> assertFalse(Files.isDirectory(path)),
			() -> assertTrue(Files.isDirectory(path.getParent())),
			() -> assertTrue(Files.isRegularFile(path)),
			() -> assertFalse(Files.isSymbolicLink(path)),
			() -> assertFalse(Files.isHidden(path)),
			() -> assertTrue(Files.isReadable(path)),
			() -> assertFalse(Files.isExecutable(path)));
	}
	
	@Test
	public void read_attributes_through_basic_file_attributes() throws IOException {
		BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
	
		assertAll(
			() -> assertFalse(data.isDirectory()),
			() -> assertTrue(data.isRegularFile()),
			() -> assertFalse(data.isSymbolicLink()));
	}
}
