# Generics and Collections

## Working with Generics

### Generic Classes

You can introduce generics into your own classes. The syntax for introducing a generic is to declare a formal type parameter in angle brackets.

```Java
public class Crate<T> {
    
    private T contents;
    
    public T emptyCrate() {
        return contents;
    }
    
    public void packCrate(T contents) {
        this.contents = contents;
    }
}
```

The generic type T is available anywhere within the Crate class. When you instantiate the class, you tell the compiler what T should be for that particular instance.  

Generic classes become useful when the classes used as the type parameter can have absolutely nothing to do with each other.  

#### Type Erasure

Specifying a generic type allows the compiler to enforce proper use of the generic type.  
Behind the scenes, the compiler replaces all references to T in Crate with Object . In other words, after the code compiles, your generics are actually just Object types. The Crate class looks like the following:

```Java
public class Crate {
    
    private Object contents;
    
    public Object emptyCrate() {
        return contents;
    }
    
    public void packCrate(Object contents) {
        this.contents = contents;
    }
}
```

This means there is only one class file. There aren’t different copies for different parameterized types.  
This process of removing the generics syntax from your code is referred to as *type erasure*. Type erasure allows your code to be compatible with older versions of Java that do not contain generics.  

The compiler adds the relevant casts for your code to work with this type of erased class. For example:

```Java
Robot r = crate.emptyCrate();
```

and the compiler turns it into:

```Java
Robot r = (Robot) crate.emptyCrate();
```

### Generic Interfaces

Just like a class, an interface can declare a formal type parameter.

```Java
public interface Shippable<T> {
    void ship(T t);
}
```

There are three ways a class can approach implementing this interface.  
The first is to specify the generic type in the class.  

```Java
class ShippableRobotCrate implements Shippable<Robot> {
    public void ship(Robot t) { }
}
```

The next way is to create a generic class.

```Java
class ShippableAbstractCrate<U> implements Shippable<U> {
    
    public void ship(U t) { }
}
```

The final way is to not use generics at all. This is the old way of writing code. It generates a compiler warning about Shippable being a raw type.

#### What You Can’t Do with Generic Types

Most of the limitations are due to type erasure. Oracle refers to types whose information is fully available at runtime as reifiable. Reifiable types can do anything that Java allows. Non-reifiable types have some limitations.  

1. *Call the constructor*. new T() is not allowed because at runtime it would be new Object().
2. *Create an array of that static type*. This one is the most annoying, but it makes sense because you’d be creating an array of Objects.
3. *Call instanceof*. This is not allowed because at runtime List<Integer> and List<String> look the same to Java thanks to type erasure.
4. *Use a primitive type as a generic type parameter*. You can use the wrapper class instead. If you want a type of int, just use Integer.
5. *Create a static variable as a generic type parameter*. This is not allowed because the type is linked to the instance of the class.  

### Generic Methods

This is often useful for *static* methods since they aren’t part of an instance that can declare the type. However, it is also
allowed on non-static methods as well.  

```Java
public static <T> Crate<T> ship(T t) {
    System.out.println("Preparing " + t);
    return new Crate<T>();
}
```

The method parameter is the generic type T . The return type is a Crate<T>. Before the return type, we declare the formal type parameter of <T>.  

### Legacy Code

In this section, we are referring to code that was written to target Java 1.4 or lower, and therefore it does not use generics. Collections written without generics are also known as *raw collections*.  

Using generics gives us compile time safety. When some code uses generics and other code does not, it is easy to get lulled into a false sense of security.

```Java
class Dragon {}
class Unicorn {}

public class LegacyDragons {

    public static void main(String[] args) {
      
        List unicorns = new ArrayList();
        unicorns.add(new Unicorn());
        printDragons(unicorns);
    }

    private static void printDragons(List<Dragon> dragons) {
        for (Dragon dragon: dragons) {
        // ClassCastException
            System.out.println(dragon);
        }
    } 
}
```

In this example, we get a *ClassCastException* on a line that is working with a generic list. At first, this seems odd. This is the problem that generics are supposed to solve. The difference is that all of the code doesn’t use generics here. The *main()* method calls *printDragons()* with a raw type. Due to type erasure, Java doesn’t know this is a problem until runtime, when it attempts to cast a Unicorn to a Dragon . The cast is tricky because it doesn’t appear in the code. With generic types, Java writes the casts for us.  
Java knows that raw types are asking for trouble, and it presents a *compiler warning* for this case.  

### Bounds

A *bounded parameter type* is a generic type that specifies a bound for the generic.  
A wildcard generic type is an unknown generic type represented with a question mark ( ? ).  
You can use generic wildcards in three ways:

1. **Unbounded wildcard**: *?*
2. **Wildcard with an upper bound**: *? extends type*
3. **Wildcard with a lower bound**: *? super type*