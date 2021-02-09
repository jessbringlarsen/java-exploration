package dk.bringlarsen.exploration.java.streams;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamExplorationTest {

    @Test
    public void createStreamOfIntegers() {
        List<Integer> result = Stream.of(1, 2, 3)
                .collect(toList());

        assertThat(result.size(), is(3));
        assertThat(result, contains(1, 2, 3));
    }

    @Test
    public void createStreamIterate() {
        LinkedList<Integer> result = Stream.iterate(1, i -> i + 1)
                    .limit(100)
                    .collect(toCollection(LinkedList::new));

        assertThat(result.getFirst(), is(1));
        assertThat(result.getLast(), is(100));
    }

    @Test
    public void createStreamOfInts() {
        LinkedList<Integer> result = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(toCollection(LinkedList::new));

        assertThat(result.getFirst(), is(1));
        assertThat(result.getLast(), is(100));
    }

    @Test
    public void createStreamOfStringsWithLimit() {
        List<String> result = Stream.generate(() -> "Person")
                .limit(20)
                .collect(toList());

        assertThat(result.size(), is(20));
        assertThat(result, everyItem(is("Person")));
    }

    @Test
    public void createStreamOfStrings() {
        List<String> result = Stream.<String>builder()
                .add("Person1")
                .add("Person2")
                .build()
                .collect(toList());

        assertThat(result.size(), is(2));
        assertThat(result, everyItem(startsWith("Person")));
    }
}
