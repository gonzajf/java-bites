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

### Purpose of an Interface

An interface provides a way for one individual to develop code that uses another individual’s code, without having access to the other individual’s underlying implementation.

## Introducing Functional Programming

Java defines a functional interface as an interface that contains a single abstract method.  
Functional interfaces are used as the basis for lambda expressions in functional programming.  
A lambda expression is a block of code that gets passed around, like an anonymous method.  

### Defining a Functional Interface

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

Java relies on context when figuring out what lambda expressions mean. We are passing this lambda as the second parameter of the *print()* method. That method expects a CheckTrait as the second parameter. Since we are passing a lambda instead, Java treats CheckTrait as a functional interface and tries to map it to the single abstract method:

```Java
boolean test(Animal a);
```

Since this interface’s method takes an Animal, it means the lambda parameter has to be an Animal. And since that interface’s method returns a boolean, we know that the lambda returns a boolean.

#### Understanding Lambda Syntax

The syntax of lambda expressions is tricky because many parts are optional. These two lines are equivalent and do the exact same thing:

```Java
a -> a.canHop()

(Animal a) -> { return a.canHop(); }
```

The left side of the arrow operator -> indicates the input parameters for the lambda expression. It can be consumed by a functional interface whose abstract method has the same number of parameters and compatible data types. The right side is referred to as the body of the lambda expression. It can be consumed by a functional interface whose abstract method returns a compatible data type.  

The parentheses () can be omitted in a lambda expression if there is exactly one input parameter and the type is not explicitly stated in the expression. This means that expressions that have zero or more than one input parameter will still require parentheses.

#### Spotting Invalid Lambdas

```Java
Duck d -> d.quack() // DOES NOT COMPILE
a,d -> d.quack() // DOES NOT COMPILE
Animal a, Duck d -> d.quack() // DOES NOT COMPILE
```

As we said, parentheses can be omitted only if there is exactly one parameter and the data type is not specified.  

Braces {} can be used around the body of the lambda expression. This allows you to write multiple lines of code in the body of the lambda expression, as you might do when working with an if statement or  while loop.  
What’s tricky here is that when you add braces {}, you must explicitly terminate each statement in the body with a semicolon;.  

We are able to omit the braces {}, semi‐colon;, and return statement for single‐line lambda bodies. This special shortcut doesn’t work when you have two or more statements.  
When using {} in the body of the lambda expression, you must use the return statement if the functional interface method that lambda implements returns a value. Alternatively, a return statement is optional when the return type of the method is void.  

Examples:

```Java
() -> true
a -> {return a.startsWith("test");}
(String a) -> a.startsWith("test")
(int x) -> {}
(int y) -> {return;}
(a, b) -> a.startsWith("test")
(String a, String b) -> a.startsWith("test")
```

The data types for the input parameters of a lambda expression are optional. When one parameter has a data type listed, though, all parameters must provide a data type.

```Java
(int y, z) -> {int x=1; return y+10; } //DOES NOT COMPILE
(String s, z) -> { return s.length()+z; } //DOES NOT COMPILE
(a, Animal b, c) -> a.getName() //DOES NOT COMPILE
```

If we add or remove all of the data types, then these lambda expressions do compile:

```Java
(y, z) -> {int x=1; return y+10; }
(String s, int z) -> { return s.length()+z; }
(a, b, c) -> a.getName()
```
