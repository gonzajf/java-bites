#Design Paterns and Principles

##Designing an Interface 

An interface is an abstract data type, similar to a class that defines a list of public abstract methods that any class implementing the interface must provide. An interface may also include constant *public* *static* *final* variables, *default* methods, and *static* methods.  

Example:  

```Java
public interface Fly {
	
	public int getWingSpan() throws Exception;
	public static final int MAX_SPEED = 100;
	
	public default void land() {
		System.out.println("Animal is landing");
	}
	
	public static double calculateSpeed(float distance, double time) {
		return distance/time;
	}
}

public class Eagle implements Fly {
	
	public int getWingSpan() {
		return 15;
	}
	
	public void land() {
		System.out.println("Eagle is diving fast");
	}
}
```

In this example, the first method of the interface, *getWingSpan()*, declares an exception in the interface. Due to the rules of method overriding, this does not require the exception to be declared in the overridden method in the Eagle class. The second declaration, MAX_SPEED, is a constant *static* variable available anywhere within our application.  
The next method, *land()*, is a *default* method that has been optionally overridden in the Eagle class. Finally, the method *calculateSpeed()* is a *static* member and, like MAX_SPEED, it is available without an instance of the interface.