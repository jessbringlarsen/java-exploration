package dk.bringlarsen.exploration.java8.streams.groupby;

import dk.bringlarsen.exploration.java8.model.Person;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GroupingByExplorationTest {

    @Test
    public void testGroupByAge() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80,"Person1"),
                Person.create(20, 80,"Person2"),
                Person.create(23, 80,"Person3"),
                Person.create(23, 80,"Person4"));

        Map<Integer, List<Person>> result = persons.stream()
                .collect(groupingBy(Person::getAge));

        assertThat(result.size(), is(2));
        assertThat(new ArrayList<>(result.values()), everyItem(hasSize(2)));
        assertThat(new ArrayList<>(result.keySet()), everyItem(isOneOf(20, 23)));
    }

    @Test
    public void testGroupByAgeAndCount() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80,"Person1"),
                Person.create(20, 80,"Person2"),
                Person.create(23, 80,"Person3"),
                Person.create(23, 80,"Person4"));

        Map<Integer, Long> result = persons.stream()
                .collect(groupingBy(Person::getAge, counting()));

        assertThat(new ArrayList<>(result.values()), everyItem(is(2L)));
    }

    @Test
    public void testGroupByAgeAndCollectUniqueNames() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80,"Person1"),
                Person.create(20, 80,"Person1"),
                Person.create(23, 80,"Person2"),
                Person.create(23, 80,"Person2"));

        Map<Integer, Set<String>> result = persons.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getName, toSet())));

        assertThat(new ArrayList<>(result.values()), everyItem(iterableWithSize(1)));
    }

    @Test
    public void testGroupByAgeSummingByWeight() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80,"Person1"),
                Person.create(20, 80,"Person2"),
                Person.create(23, 80,"Person3"),
                Person.create(23, 80,"Person4"));

        Map<Integer, Integer> result = persons.stream()
                .collect(groupingBy(Person::getAge, summingInt(Person::getWeight)));

        assertThat(new ArrayList<>(result.values()), everyItem(is(160)));
    }
}