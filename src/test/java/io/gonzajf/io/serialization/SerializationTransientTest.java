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

import io.gonzajf.serialization.AnimalTransient;

public class SerializationTransientTest {

	@Test
	public void serialization_test() throws IOException, ClassNotFoundException {
		
		File dataFile = setUp();
		List<AnimalTransient> animalsFromFile = getAnimals(dataFile);
		
		assertAll(
			() -> assertFalse(animalsFromFile.isEmpty()),
			() -> assertTrue(animalsFromFile.stream().allMatch(a -> a.getName() == null)),
			() -> assertTrue(animalsFromFile.stream().allMatch(a -> a.getAge() == 0)),
			() -> assertTrue(animalsFromFile.stream().allMatch(a -> a.getType() == 'P')));
	}
	
	private File setUp() throws IOException {
		List<AnimalTransient> animals = new ArrayList<AnimalTransient>();
		animals.add(new AnimalTransient("Tommy Tiger", 5, 'T'));
		animals.add(new AnimalTransient("Peter Penguin", 8, 'P'));
		File dataFile = new File("src/test/resources", "AnimalTransient.data");
		createAnimalTransientsFile(animals, dataFile);
		return dataFile;
	}


	public static void createAnimalTransientsFile(List<AnimalTransient> animals, File dataFile) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for (AnimalTransient animal : animals)
				out.writeObject(animal);
		}
	}

	public static List<AnimalTransient> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
		List<AnimalTransient> animals = new ArrayList<AnimalTransient>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			while (true) {
				Object object = in.readObject();
				if (object instanceof AnimalTransient)
					animals.add((AnimalTransient) object);
			}
		} catch (EOFException e) {
			// File end reached
		}
		return animals;
	}
}