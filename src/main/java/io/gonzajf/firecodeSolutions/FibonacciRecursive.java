package io.gonzajf.firecodeSolutions;

public class FibonacciRecursive {
	
	/**
	 * The Fibonacci Sequence is the series of numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ... 
	 * The next number is found by adding up the two numbers before it.
	 * Write a recursive method fib(n) that returns the nth Fibonacci number. n is 0 indexed, 
	 * which means that in the sequence 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ..., n == 0 should return 0 and n == 3 should return 2. 
	 * Assume n is less than 15.
	 */
	public static int fib(int n) {

		if(n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		} else {
			return fib(n-1) + fib(n-2);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(fib(4));
	}
}
