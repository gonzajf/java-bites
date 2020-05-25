package io.gonzajf.concurrency;

public class PrintData implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Printing record: "+i);
		}		
	}

	public static void main(String[] args) {
		System.out.println("begin");
		(new ReadInventoryThread()).run();;
		(new Thread(new PrintData())).run();
		(new ReadInventoryThread()).run();
		System.out.println("end");
	}
}

class ReadInventoryThread extends Thread {
	public void run() {
		System.out.println("Printing zoo inventory");
	}
}