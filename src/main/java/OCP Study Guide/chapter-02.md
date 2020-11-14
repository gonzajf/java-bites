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

## Implementing Polymorphism

Polymorphism is the ability of a single interface to support multiple underlying forms. In Java, this allows multiple types of objects to be passed to a single method or class.  
Example:

```Java
public interface LivesInOcean { public void makeSound(); }
	
public class Dolphin implements LivesInOcean {
	public void makeSound() { System.out.println("whistle"); }
}

public class Whale implements LivesInOcean {
	public void makeSound() { System.out.println("sing"); }
}

public class Oceanographer {
	
	public void checkSound(LivesInOcean animal) {
		animal.makeSound();
	}

	public void main(String[] args) {
		Oceanographer o = new Oceanographer();
		o.checkSound(new Dolphin());
		o.checkSound(new Whale());
	}
}
```

This code compiles and executes without issue and yields the following output:  

```Java
whistle
sing
```

In this sample code, our Oceanographer class includes a method named *checkSound()* that is capable of accepting any object whose class implements the LivesInOcean interface.  

Polymorphism also allows one object to take on many different forms.  
A Java object may be accessed using a reference with the same type as the object, a reference that is a superclass of the object, or a reference that defines an interface that the object implements, either directly or through a superclass. Furthermore, a cast is not required if the object is being reassigned to a supertype or interface of the object.  
Example:

```Java
public class Primate {

	public boolean hasHair() {
		return true;
	}
}

public interface HasTail {
	public boolean isTailStriped();
}

public class Lemur extends Primate implements HasTail {

	public int age = 10;
	
	public boolean isTailStriped() {
		return false;
	}

	public static void main(String[] args) {
		Lemur lemur = new Lemur();
		System.out.println(lemur.age);
		HasTail hasTail = lemur;
		System.out.println(hasTail.isTailStriped());
		Primate primate = lemur;
		System.out.println(primate.hasHair());
	}
}
```

The most important thing to note about this example is that only one object, Lemur, is created and referenced. The ability of the Lemur object to be passed as an instance of an interface it implements, HasTail, as well as an instance of one of its superclasses, Primate, is the nature of polymorphism.  
If you use a variable to refer to an object, then only the methods or variables that are part of the variable’s reference type can be called without an explicit cast.
For example:

```Java
HasTail hasTail = lemur;
System.out.println(hasTail.age); // DOES NOT COMPILE

Primate primate = lemur;
System.out.println(primate.isTailStriped()); // DOES NOT COMPILE
```

The reference hasTail has direct access only to methods defined with the HasTail interface; therefore, it doesn’t know that the variable age is part of the object. Likewise, the reference primate has access only to methods defined in the Primate class,
and it doesn’t have direct access to the isTailStriped() method.  

### Distinguishing between an Object and a Reference

In Java, all objects are accessed by reference, so as a developer you never have direct access to the memory of the object itself.
Conceptually, though, you should consider the object as the entity that exists in memory, allocated by the Java runtime environment. Regardless of the type of the reference that you have for the object in memory, the object itself doesn’t change.  

We can summarize this principle with the following two rules:

1. The type of the object determines which properties exist within the object in memory.
2. The type of the reference to the object determines which methods and variables are
accessible to the Java program.  

It therefore follows that successfully changing a reference of an object to a new reference type may give you access to new properties of the object, but those properties existed before the reference change occurred.  

### Casting Object References

Here are some basic rules to keep in mind when casting variables:

1. Casting an object from a subclass to a superclass doesn’t require an explicit cast.
2. Casting an object from a superclass to a subclass requires an explicit cast.
3. The compiler will not allow casts to unrelated types.
4. Even when the code compiles without issue, an exception may be thrown at runtime if the object being cast is not actually an instance of that class.  
