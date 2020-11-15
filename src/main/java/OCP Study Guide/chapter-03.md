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

