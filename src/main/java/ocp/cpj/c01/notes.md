# Design Paterns and Principles

## Designing an Interface 

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

In this example, the first method of the interface, *getWingSpan()*, declares an exception in the interface. Due to the rules of method overriding, this does not require the exception to be declared in the overridden method in the Eagle class. The second declaration, *MAX_SPEED*, is a constant *static* variable available anywhere within our application.  
The next method, *land()*, is a *default* method that has been optionally overridden in the Eagle class. Finally, the method *calculateSpeed()* is a *static* member and, like *MAX_SPEED*, it is available without an instance of the interface.

The compiler automatically adds public to all interface methods and abstract to all non‐static and non‐default methods, if the developer does not provide them. By contrast, the class implementing the
interface must provide the proper modifiers.  

Remember that an interface cannot extend a class, nor can a class extend an interface.  

Interfaces also serve to provide limited support for multiple inheritance within the Java language, as a class may implement multiple interfaces.  

You can also construct interfaces that have neither methods nor class members, traditionally referred to as marker interfaces.

#### Purpose of an Interface

An interface provides a way for one individual to develop code that uses another individual’s code, without having access to the other individual’s underlying implementation.

## Introducing Functional Programming

Java defines a functional interface as an interface that contains a single abstract method.
Functional interfaces are used as the basis for lambda expressions in functional programming.
A lambda expression is a block of code that gets passed around, like an anonymous method.

#### Defining a Functional Interface

Let’s take a look at an example of a functional interface and a class that implements it:

```Java
@FunctionalInterface
public interface Sprint {
	public void sprint(Animal animal);
}

public class Tiger implements Sprint {
	
	public void sprint(Animal animal) {
		System.out.println("Animal is sprinting fast! "+animal.toString());
	}
}
```
In this example, the Sprint class is a functional interface, because it contains exactly one abstract method, and the Tiger class is a valid class that implements the interface. 

While it is a good practice to mark a functional interface with the @FunctionalInterface annotation for clarity, it is not required with functional programming. The Java compiler implicitly assumes that any interface that contains exactly one abstract method is a functional interface. Conversely, if a class marked with the *@FunctionalInterface* annotation contains more than one abstract method, or no abstract methods at all, then the compiler will detect this error and not compile. 

#### Implementing Functional Interfaces with Lambdas

```Java
public class Animal {
	
	private String species;
	private boolean canHop;
	private boolean canSwim;

	public Animal(String speciesName, boolean hopper, boolean swimmer) {
		species = speciesName;
		canHop = hopper;
		canSwim = swimmer;
	}

	public boolean canHop() { return canHop; }
	public boolean canSwim() { return canSwim; }
	public String toString() { return species; }
}

public interface CheckTrait {
	public boolean test(Animal a);
}
```
The following simple program uses a lambda expression to determine if some sample animals match the specified criteria: 

```Java
public class FindMatchingAnimals {

	private static void print(Animal animal, CheckTrait trait) {
		
		if(trait.test(animal))
			System.out.println(animal);
	}

	public static void main(String[] args) {
	
		print(new Animal("fish", false, true), a -> a.canHop());
		print(new Animal("kangaroo", true, false), a -> a.canHop());
	}
}
```

Java relies on context when fi guring out what lambda expressions mean. We are passing this lambda as the second parameter of the print() method. That method expects a CheckTrait as the second parameter. Since we are passing a lambda instead, Java treats CheckTrait as a functional interface and tries to map it to the single abstract method:

```Java
boolean test(Animal a);
```
Since this interface’s method takes an Animal , it means the lambda parameter has to be an Animal . And since that interface’s method returns a boolean , we know that the lambda returns a boolean.