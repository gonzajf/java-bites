package io.gonzajf.workingWithInterfaces.birds;

public class Eagle implements Fly {

	@Override
	public int getWingSpan() throws Exception {
		return 15;
	}

	public void land() {
		System.out.println("Eagle is diving fast");
	}
}
