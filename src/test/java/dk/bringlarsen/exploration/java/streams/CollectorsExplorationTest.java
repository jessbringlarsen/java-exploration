package dk.bringlarsen.exploration.java.streams;

import dk.bringlarsen.exploration.java.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CollectorsExplorationTest {

    @Test
    public void testToMap() {
        List<Person> persons = Arrays.asList(
                Person.create(21, "Person1"),
                Person.create(22, "Person2"));

        Map<Integer, Person> result = persons.stream()
                .collect(toMap(Person::getAge, Function.identity()));

        assertThat(result.get(21).getName(), is("Person1"));
        assertThat(result.get(22).getName(), is("Person2"));
    }
}
