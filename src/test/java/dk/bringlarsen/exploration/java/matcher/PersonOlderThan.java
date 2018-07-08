package dk.bringlarsen.exploration.java.matcher;

import dk.bringlarsen.exploration.java.model.Person;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class PersonOlderThan extends TypeSafeMatcher<Person> {

    private int age;

    private PersonOlderThan(int age) {
        this.age = age;
    }

    @Override
    protected boolean matchesSafely(Person person) {
        return person.getAge() > age;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not older than " + age);
    }

    @Factory
    public static Matcher personOlderThan(int age) {
        return new PersonOlderThan(age);
    }
}
