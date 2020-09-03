package io.gonzajf.concurrency;

public class PrintData implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Printing record: "+i);
		}		
	}

	public static void main(String[] args) {
		//the execution order is unknown until 
		//this sample uses four the total of four threads:
		//the main user thread and the three additional threads created by the main method.
		System.out.println("begin");
		(new ReadInventoryThread()).start();
		(new Thread(new PrintData())).start();
		(new ReadInventoryThread()).start();
		System.out.println("end");
	}
}

class ReadInventoryThread extends Thread {
	public void run() {
		System.out.println("Printing zoo inventory");
	}
}