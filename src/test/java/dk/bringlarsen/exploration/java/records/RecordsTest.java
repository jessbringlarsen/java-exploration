package dk.bringlarsen.exploration.java.records;

import dk.bringlarsen.exploration.java.model.Car;
import dk.bringlarsen.exploration.java.model.Car.Build;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordsTest {

    @Test
    void testRecordEqualsAndHashCode() {
        Car car1 = new Car("Honda", "Jazz");
        Car car2 = Build.car()
                .withName("Honda")
                .withModel("Jazz")
                .build();

        assertEquals(car1, car2);
        assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    void testToString() {
        Car car = new Car("Honda", "Jazz");

        assertEquals("Car[make=Honda, model=Jazz]", car.toString());
    }

    @Test
    void testConstructor() {
        Car car = new Car("Honda");

        assertEquals("Car[make=Honda, model=unknown]", car.toString());
    }
}
