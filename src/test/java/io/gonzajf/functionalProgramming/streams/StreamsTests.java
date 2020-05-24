package io.gonzajf.functionalProgramming.streams;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StreamsTests {

	private Stream<String> stream;
	
	@BeforeEach
	public void setUp() {
		stream = Stream.of("java", "scala", "python");
	}

	//The count() method determines the number of elements in a finite stream
	@Test
	public void count_test() {
		assertEquals(3, stream.count());
	}
	
	//The min() and max() methods allow you to pass a custom comparator and find the smallest
	//or largest value in a finite stream according to that sort order.
	@Test
	public void min_test() {
	
		Comparator<String> lengthComparator = (s1, s2) -> s1.length() - s2.length();
		
		Optional<String> min = stream.min(lengthComparator);
//		Optional<String> min = stream.max(lengthComparator);
		
		assertEquals("java", min.get());
//		assertEquals("python", max.get());
	}
	
	@Test
	public void find_first_test() {
		assertEquals("java", stream.findFirst().get());
	}
	
	@Test
	public void find_any_test() {
		assertNotEquals(Optional.empty(), stream.findAny());
	}
	
	//The allMatch(), anyMatch() and noneMatch() methods search a stream and return 
	//information about how the stream pertains to the predicate.
	@Test
	public void matches_test() {
		
		Predicate<String> lengthGreaterThanFour = x -> x.length() > 4;
		
		assertFalse(stream.allMatch(lengthGreaterThanFour));
//		assertFalse(stream.noneMatch(lengthGreaterThanFour));
//		assertTrue(stream.anyMatch(lengthGreaterThanFour));
	}
	
//	The reduce() method combines a stream into a single object.
	@Test
	public void reduce_test() {
		assertEquals("javascalapython", stream.reduce("", String::concat));
	}
	
//	The collect() method is a special type of reduction called a mutable reduction. It is more 
//	efficient than a regular reduction because we use the same mutable object while accumulating.
//	This is a really useful method, because it lets us get data out of streams and into another form
	@Test
	public void collect_test() {
		
		TreeSet<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
		
		assertAll(() -> assertEquals(3, set.size()),
					() -> assertEquals("scala", set.last()));
	}
	
	//The filter() method returns a Stream with elements that match a given expression
	@Test
	public void filter_test() {
		assertEquals(2, stream.filter(x -> x.length() > 4).count());
	}
	
	//The limit() and skip() methods make a Stream smaller. They could make a finite stream
	//smaller, or they could make a finite stream out of an infinite stream.
	@Test
	public void limite_skip_test() {
		assertEquals(1, stream.skip(2).limit(5).count());
	}
	
	//The map() method creates a one-to-one mapping from the elements in the stream to the
	//elements of the next step in the stream.
	@Test
	public void map_test() {
		assertEquals("JAVA", stream.map(String::toUpperCase).findFirst().get());
	}
	
	//The sorted() method returns a stream with the elements sorted.
	public void sorted_test() {
		assertEquals("scala", stream.sorted(Comparator.reverseOrder()).findFirst().get());
	}
}