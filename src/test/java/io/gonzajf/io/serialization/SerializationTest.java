package io.gonzajf.io.serialization;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.gonzajf.serialization.Animal;

public class SerializationTest {


	@Test
	public void serialization_test() throws IOException, ClassNotFoundException {
		
		File dataFile = setUp();
	
		List<Animal> animalsFromFile = getAnimals(dataFile);
		
		assertAll(
			() -> assertFalse(animalsFromFile.isEmpty()),
			() -> assertTrue(animalsFromFile.stream().anyMatch(a -> a.getName().equals("Tommy Tiger"))),
			() -> assertTrue(animalsFromFile.stream().anyMatch(a -> a.getAge() == 5)));
	}
	
	private File setUp() throws IOException {
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("Tommy Tiger", 5, 'T'));
		animals.add(new Animal("Peter Penguin", 8, 'P'));
		File dataFile = new File("src/test/resources", "animal.data");
		createAnimalsFile(animals, dataFile);
		return dataFile;
	}

	public static void createAnimalsFile(List<Animal> animals, File dataFile) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for (Animal animal : animals)
				out.writeObject(animal);
		}
	}

	public static List<Animal> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
		List<Animal> animals = new ArrayList<Animal>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			while (true) {
				Object object = in.readObject();
				if (object instanceof Animal)
					animals.add((Animal) object);
			}
		} catch (EOFException e) {
			// File end reached
		}
		return animals;
	}
}