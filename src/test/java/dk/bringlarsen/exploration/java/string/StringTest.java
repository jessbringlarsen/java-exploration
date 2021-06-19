package dk.bringlarsen.exploration.java.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

class StringTest {

    @Test
    void testMultilineString() throws Exception {
        String multilineString = """
                line 1
                line 2
                """;

        String expectedText = Files.readString(Paths.get(StringTest.class.getResource("sometext.txt").toURI()));

        Assertions.assertEquals(expectedText, multilineString);
    }
}
