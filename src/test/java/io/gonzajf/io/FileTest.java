package io.gonzajf.io;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

public class FileTest {

	private String path = "src/test/resources";
	private String otherPath = "src/test/java/io/gonzajf/collections";

	@Test
	public void file_exist_test() {

		File file = new File(path, "exampleFile.txt");
		assertAll(() -> assertTrue(file.exists()), 
				() -> assertEquals("exampleFile.txt", file.getName()),
				() -> assertFalse(file.isDirectory()), 
				() -> assertTrue(file.isFile()));
	}

	@Test
	public void file_input_output_stream_test() throws FileNotFoundException, IOException {

		File source = new File(otherPath, "CollectionsTest.java");
		File destination = new File(path, "CollectionsTestCopy.java");

		copyWithStreams(source, destination);
		assertAll(() -> assertTrue(destination.exists()),
				() -> assertEquals("CollectionsTestCopy.java", destination.getName()),
				() -> assertFalse(destination.isDirectory()), 
				() -> assertTrue(destination.isFile()),
				() -> assertTrue(destination.length() > 0));

	}

	private void copyWithStreams(File source, File destination) throws IOException {

		try (InputStream in = new FileInputStream(source); 
				OutputStream out = new FileOutputStream(destination)) {
			
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		}
	}
	
	@Test
	public void file_buffered_input_output_stream_test() throws FileNotFoundException, IOException {

		File source = new File(otherPath, "CollectionsTest.java");
		File destination = new File(path, "CollectionsTestBufferedCopy.java");

		copyWithBufferedStreams(source, destination);
		assertAll(() -> assertTrue(destination.exists()),
				() -> assertEquals("CollectionsTestBufferedCopy.java", destination.getName()),
				() -> assertFalse(destination.isDirectory()), 
				() -> assertTrue(destination.isFile()),
				() -> assertTrue(destination.length() > 0));

	}

	private void copyWithBufferedStreams(File source, File destination) throws IOException {

		try (InputStream in = new BufferedInputStream(new FileInputStream(source));
				OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
			
			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, lengthRead);
				out.flush();
			}
		}
	}
}