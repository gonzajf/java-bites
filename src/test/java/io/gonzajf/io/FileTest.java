package io.gonzajf.io;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FileTest {

	private String path = "src/test/resources";
	
	@Test
	public void file_exist_test() {
		
		File file = new File(path, "exampleFile.txt");
		assertAll(
			() -> assertTrue(file.exists()),
			() -> assertEquals("exampleFile.txt", file.getName()),
			() -> assertFalse(file.isDirectory()),
			() -> assertTrue(file.isFile()));
	}
	
	
}
