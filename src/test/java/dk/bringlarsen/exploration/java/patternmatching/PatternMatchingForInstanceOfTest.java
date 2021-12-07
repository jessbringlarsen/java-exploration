package dk.bringlarsen.exploration.java.patternmatching;


import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@JDK(version = 15, description = "Pattern matching for type checks")
class PatternMatchingForInstanceOfTest {

    @Test
    void testPatterMatchingForInstanceOf() {
        Object string = "SomeString";

        if(string instanceof String s) {
            assertEquals("SomeString", s);
        } else {
            fail("Expected a string");
        }
    }
}
