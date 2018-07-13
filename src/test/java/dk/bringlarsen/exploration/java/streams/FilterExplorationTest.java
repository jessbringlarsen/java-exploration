package dk.bringlarsen.exploration.java.streams;

import dk.bringlarsen.exploration.java.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static dk.bringlarsen.exploration.java.hamcrest.matcher.PersonOlderThan.personOlderThan;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.Assert.assertThat;

public class FilterExplorationTest {

    @Test
    public void reduceToPersonOver18() {
        List<Person> persons = Arrays.asList(
                Person.create(10, "Person1"),
                Person.create(19, "Person2"),
                Person.create(20, "Person3"));

        List<Person> result = persons.stream()
                .filter(p -> p.getAge() > 18)
                .collect(toList());

        assertThat(result.size(), is(2));
        assertThat(result, everyItem(personOlderThan(18)));
    }
}
