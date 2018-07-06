package dk.bringlarsen.exploration.java8.model;

import java.util.Objects;

public class Person {
    private int age;
    private int weight;
    private String name;


    public static Person create(int age, int weight, String name) {
        return new Person(age, weight, name);
    }

    public static Person create(int age, String name) {
        return new Person(age, 80, name);
    }

    public static Person create(String name) {
        return new Person(20, 80, name);
    }

    private Person(int age, int weight, String name) {
        this.age = age;
        this.weight = weight;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                weight == person.weight &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, weight, name);
    }
}