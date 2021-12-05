package dk.bringlarsen.exploration.java.model;

import dk.bringlarsen.exploration.java.JDK;

/**
 * Properties of a record:
 * <ul>
 * <li>private, final field for each piece of data
 * <li>getter for each field
 * <li>public constructor with a corresponding argument for each field
 * <li>equals method that returns true for objects of the same class when all fields match
 * <li>hashCode method that returns the same value when all fields match
 * <li>toString method that includes the name of the class and the name of each field and its corresponding value
 * </ul>
 */
@JDK(version = 14, description = "Java records")
public record Car(String make, String model) {

    public Car(String make) {
        this(make, "unknown");
    }

    public static class Build {
        private String make, model;

        private Build() {
        }

        public static Build car() {
            return new Build();
        }

        public Build withName(String make) {
            this.make = make;
            return this;
        }

        public Build withModel(String model) {
            this.model = model;
            return this;
        }

        public Car build() {
            return new Car(make, model);
        }
    }
}
