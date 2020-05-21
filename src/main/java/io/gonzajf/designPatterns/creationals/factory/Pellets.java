package io.gonzajf.designPatterns.creationals.factory;

public class Pellets extends Food {
	
	public Pellets(int quantity) {
		super(quantity);
	}

	public void consumed() {
		System.out.println("Pellets eaten: " + getQuantity());
	}
}