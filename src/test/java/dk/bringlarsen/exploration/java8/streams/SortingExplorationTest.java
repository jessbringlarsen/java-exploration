package dk.bringlarsen.exploration.java8.streams;

import dk.bringlarsen.exploration.java8.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class SortingExplorationTest {

    @Test
    public void testSortAscending() {
        List<Person> persons = Arrays.asList(
                Person.create("Person1"),
                Person.create("Person2"));

        List<String> result = persons.stream()
                .map(Person::getName)
                .sorted()
                .collect(toList());

        assertThat(result.size(), is(2));
        assertThat(result, contains("Person1", "Person2"));
    }

    @Test
    public void testSortDescending() {
        List<Person> persons = Arrays.asList(
                Person.create("Person1"),
                Person.create("Person2"));

        List<String> result = persons.stream()
                .map(Person::getName)
                .sorted(reverseOrder())
                .collect(toList());

        assertThat(result.size(), is(2));
        assertThat(result, contains("Person2", "Person1"));
    }
}
