package io.gonzajf.nio2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class PathTest {

	@Test
	public void path_creation_test() {
		Path path1 = Paths.get("src/test/resources/exampleFile.txt");
		Path path2 = Paths.get("src", "test", "resources", "exampleFile.txt");
		assertEquals(path1, path2);
	}
	
}
