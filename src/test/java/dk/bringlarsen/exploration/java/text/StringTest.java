package dk.bringlarsen.exploration.java.text;

import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringTest {

    @Test
    @JDK(version = 15, description = "Multiline strings")
    void testMultilineString() throws Exception {
        String multilineString = """
                line 1
                line 2
                """;

        String expectedText = Files.readString(Paths.get(StringTest.class.getResource("sometext.txt").toURI()));

        assertEquals(expectedText, multilineString);
    }

    @Test
    @JDK(version = 12, description = "New String#indent method")
    void testIndentMethod() {
        String string = "someString";

        String indentedString = string.indent(2);

        assertTrue(indentedString.startsWith("  someString"));
    }

    @Test
    @JDK(version = 12, description = "New String#transform method")
    void testTransform() {
        String string = "someString";

        string = string.transform(String::toUpperCase);

        assertEquals("SOMESTRING", string);
    }
}
