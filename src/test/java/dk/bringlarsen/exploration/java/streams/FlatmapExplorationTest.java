package dk.bringlarsen.exploration.java.streams;

import dk.bringlarsen.exploration.java.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class FlatmapExplorationTest {

    @Test
    public void testGetAllVisitedCountires() {
        List<Person> persons = Arrays.asList(
                Person.create("Person1").addCountryVisited("Denmark"),
                Person.create("Person2").addCountryVisited("Germany"));

        Set<String> result = persons.stream()
                .flatMap(person -> person.getCountriesVisited().stream())
                .collect(toSet());

        assertThat(result.size(), is(2));
        assertThat(result, containsInAnyOrder("Denmark", "Germany"));
    }
}
