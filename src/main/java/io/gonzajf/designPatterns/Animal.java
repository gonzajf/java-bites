package io.gonzajf.designPatterns;

import java.util.List;

public class Animal {

	private String name;
	private int age;
	private List<String> favouriteFoods;

	private Animal(String n, int a, List<String> ff) {
		name = n;
		age = a;
		favouriteFoods = ff;
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public List<String> getFavouriteFoods() {
		return favouriteFoods;
	}
	
	static class AnimalBuilder {

		private String name;
		private int age;
		private List<String> favouriteFoods;
		
		public AnimalBuilder setName(String n) {
			name = n;
			return this;
		}

		public AnimalBuilder setAge(int a) {
			this.age = a;
			return this;
		}

		public AnimalBuilder setFavouriteFoods(List<String> ff) {
			this.favouriteFoods = ff;
			return this;
		}
		
		public Animal build() {
			return new Animal(name, age, favouriteFoods);
		}
	}
}