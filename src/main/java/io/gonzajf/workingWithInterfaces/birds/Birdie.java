package io.gonzajf.workingWithInterfaces.birds;

public class Birdie implements Fly {

	@Override
	public int getWingSpan() throws Exception {
		return 10;
	}
}