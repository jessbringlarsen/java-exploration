package dk.bringlarsen.exploration.java.streams;

import dk.bringlarsen.exploration.java.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

/**
 * Use Reduce when you want to reduce a collection to a single item.
 */
public class ReduceExplorationTest {

    @Test
    public void reduceToYoungestPerson() {
        List<Person> persons = Arrays.asList(
                Person.create(10, "Person1"),
                Person.create(19, "Person2"));

        Optional<Person> result = persons.stream()
                .reduce(BinaryOperator.minBy(Comparator.comparingInt(Person::getAge)));

        assertThat(result.get().getAge(), is(10));
    }

    @Test
    public void ageOfAllPersons() {
        List<Person> persons = Arrays.asList(
                Person.create(10, "Person1"),
                Person.create(19, "Person2"));

        int age = persons.stream()
            .map(Person::getAge)
            .reduce(0, Integer::sum);

        assertThat(age, is(29));
    }
}
