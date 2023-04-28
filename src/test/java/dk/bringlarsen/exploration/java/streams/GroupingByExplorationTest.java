package dk.bringlarsen.exploration.java.streams;

import dk.bringlarsen.exploration.java.model.Person;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GroupingByExplorationTest {

    @Test
    public void testGroupByAge() {
        List<Person> persons = Arrays.asList(
                Person.create(20, "Person1"),
                Person.create(20, "Person2"),
                Person.create(23, "Person3"),
                Person.create(23, "Person4"));

        Map<Integer, List<Person>> result = persons.stream()
                .collect(groupingBy(Person::getAge));

        assertThat(result.size(), is(2));
        assertThat(new ArrayList<>(result.values()), everyItem(hasSize(2)));
        assertThat(new ArrayList<>(result.keySet()), everyItem(isOneOf(20, 23)));
    }

    @Test
    public void testGroupByAgeAndCount() {
        List<Person> persons = Arrays.asList(
                Person.create(20, "Person1"),
                Person.create(20, "Person2"),
                Person.create(23, "Person3"),
                Person.create(23, "Person4"));

        Map<Integer, Long> result = persons.stream()
                .collect(groupingBy(Person::getAge, counting()));

        assertThat(result.size(), is(2));
        assertThat(new ArrayList<>(result.values()), everyItem(is(2L)));
    }

    @Test
    public void testGroupByAgeAndCollectUniqueNames() {
        List<Person> persons = Arrays.asList(
                Person.create(20, "Person1"),
                Person.create(20, "Person1"),
                Person.create(23, "Person2"),
                Person.create(23, "Person2"));

        Map<Integer, Set<String>> result = persons.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getName, toSet())));

        assertThat(result.size(), is(2));
        assertThat(new ArrayList<>(result.values()), everyItem(iterableWithSize(1)));
    }

    @Test
    public void testGroupByAgeSummingByWeight() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80, "Person1"),
                Person.create(20, 80, "Person2"),
                Person.create(23, 80, "Person3"),
                Person.create(23, 80, "Person4"));

        Map<Integer, Integer> result = persons.stream()
                .collect(groupingBy(Person::getAge, summingInt(Person::getWeight)));

        assertThat(result.size(), is(2));
        assertThat(new ArrayList<>(result.values()), everyItem(is(160)));
    }

    @Test
    public void testGroupByAgeAndFindOldestPersons() {
        List<Person> persons = Arrays.asList(
                Person.create(20, 80, "Person1"),
                Person.create(20, 80, "Person2"),
                Person.create(23, 80, "Person3"),
                Person.create(23, 80, "Person4"));

        Map.Entry<Integer, Long> personsGroupedByAge = persons.stream()
                .collect(groupingBy(Person::getAge, counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .get();

        assertThat(personsGroupedByAge.getKey(), is(23));
        assertThat(personsGroupedByAge.getValue(), is(2L));
    }
}