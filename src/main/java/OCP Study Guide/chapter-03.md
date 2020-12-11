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

#### Unbounded Wildcards

An unbounded wildcard represents any data type. You use *?* when you want to specify that any type is OK with you.  

```Java
public static void printList(List<?> list) {
    for (Object x: list) System.out.println(x);
}
public static void main(String[] args) {
    List<String> keywords = new ArrayList<>();
    keywords.add("java");
    printList(keywords);
}
```

*printList()* takes any type of list as a parameter. keywords is a list of type String and matches to be a list of anything. “Anything” just happens to be a String here.  

#### Upper-Bounded Wildcards

Let’s try to write a method that adds up the total of a list of numbers. We’ve established that a generic type can’t just use a subclass:  

```Java
ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE
```

Instead, we need to use a wildcard:

```Java
ArrayList<? extends Number> list = new ArrayList<Integer>();
```

The upper-bounded wildcard says that any class that extends Number or Number itself can be used as the formal parameter type.  

```Java
public static long total(List<? extends Number> list) {
    long count = 0;
    for (Number number: list)
        count += number.longValue();
    return count;
}
```

Java converts the previous code to something equivalent to the following (due to type erasure):

```Java
public static long total(List list) {
    long count = 0;
    for (Object obj: list) {
        Number number = (Number) obj;
        count += number.longValue();
    }
return count;
}
```

Something interesting happens when we work with upper bounds or unbounded wildcards. The list becomes logically immutable.  

#### Lower-Bounded Wildcards

Let’s try to write a method that adds a string “quack” to two lists:  

```Java
List<String> strings = new ArrayList<String>();
strings.add("tweet");
List<Object> objects = new ArrayList<Object>(strings);
addSound(strings);
addSound(objects);
```

The problem is that we want to pass a List<String> and a List<Object> to the same method.
To solve this problem, we need to use a lower bound:

```Java
public static void addSound(List<? super String> list) { //lower bound
    list.add("quack");
}
```

With a lower bound, we are telling Java that the list will be a list of String objects or a list of some objects that are a superclass of String. Either way, it is safe to add a String to that list.  

## Using Lists, Sets, Maps, and Queues

A *collection* is a group of objects contained in a single object. The *Java Collections Framework* is a set of classes in java.util for storing collections. There are four main interfaces in the Java Collections Framework:

1. List: an ordered collection of elements that allows duplicate entries. Elements in a list can be accessed by an *int* index.
2. Set: a collection that does not allow duplicate entries.
3. Queue: a collection that order its elements in a specified order for processing.
4. Map: a collection that maps keys to values, witho no duplicates keys allowed. The elements in a map are key/value pairs.

### Using the *List* Interface

You use a list when you want an ordered collection that can contain duplicate entries. Items can be retrieved and inserted at specific positions in the list based on an int index much like an array.  
Sometimes, you don’t actually care about the order of elements in a list. List is like the “go to” data type.  
The main thing that all List implementations have in common is that they are ordered and allow duplicates. Beyond that, they each offer different functionality.

#### Comparing *List* Implementations

##### ArrayList

An ArrayList is like a resizable array. When elements are added, the ArrayList automatically grows. When you aren’t sure which collection to use, use an ArrayList.  
The main benefit of an ArrayList is that you can look up any element in constant time. Adding or removing an element is slower than accessing an element. This makes an ArrayList a good choice when you are reading more often than (or the same amount as) writing to the ArrayList.  

##### LinkedList

A LinkedList is special because it implements both *List* and *Queue* interfaces and it has additional methods to facilitate adding or removing from the beginning and/or end of the list.  
The main benefits of a LinkedList are that you can access, add, and remove from the beginning and end of the list in constant time. The tradeoff is that dealing with an arbitrary index takes linear time. This makes a LinkedList a good choice when you’ll be usingit as *Queue*.

### Using the *Set* Interface

You use a set when you don’t want to allow duplicate entries.

#### Comparing *Set* Implementations

##### HashSet

A HashSet stores its elements in a hash table. This means that it uses the *hashCode()* method of the objects to retrieve them more efficiently.  
The main benefit is that adding elements and checking if an element is in the set both have constant time. The tradeoff is that you lose the order in which you inserted the elements. Most of the time, you aren’t concerned with this in a set anyway, making HashSet the most common set.

##### TreeSet

A TreeSet stores its elements in a sorted tree structure. The main benefit is that the set is always in sorted order. The tradeoff is that adding and checking if an element is present are both *O(log n)*. TreeSet implements a special interface called NavigableSet, which lets you slice up the collection.  

### Using the *Queue* Interface

You use a queue when elements are added and removed in a specific order. Queues are typically used for sorting elements prior to processing them.  

#### Comparing *Queue* Implementations

In addition to being a list, a LinkedList, is a double-ended queue. A double-ended queue is different from a regular queue in that you can insert and remove elements from both the front and back of the queue.  
The main benefit of a LinkedList is that it implements both the List and Queue interfaces. The tradeoff is that it isn’t as efficient as a “pure” queue.  
An ArrayDeque is a “pure” double-ended queue and it stores its elements in a resizable array. The main benefit of an ArrayDeque is that it is more efficient than a LinkedList.  

### Map

You use a map when you want to identify values by a key.  

#### Comparing *Map* Implementations

A HashMap stores the keys in a hash table. This means that it uses the *hashCode()* method of the keys to retrieve their values more efficiently.  
The main benefit is that adding elements and retrieving the element by key both have constant time. The tradeoff is that you lose the order in which you inserted the elements. Most of the time, you aren’t concerned with this in a map anyway. If you were, you could use LinkedHashMap.  
A TreeMap stores the keys in a sorted tree structure. The main benefit is that the keys are always in sorted order. The tradeoff is that adding and checking if a key is present are both *O(log n)*.  

## *Comparator* vs. *Comparable*

You can also sort objects that you create. Java provides an interface called *Comparable*. If your class implements *Comparable*, it can be used in these data structures that require comparison.  
There is also a class called *Comparator*, which is used to specify that you want to use a different order than the object itself provides.  