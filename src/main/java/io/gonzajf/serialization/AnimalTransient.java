package io.gonzajf.serialization;

import java.io.Serializable;

public class AnimalTransient implements Serializable {

	private static final long serialVersionUID = 2L;
	private transient String name;
	private transient int age = 10;
	private static char type = 'C';

	{
		this.age = 14;
	}

	public AnimalTransient() {
		this.name = "Unknown";
		this.age = 12;
		this.type = 'Q';
	}

	public AnimalTransient(String name, int age, char type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	public char getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", age=" + age + ", type=" + type + "]";
	}
}