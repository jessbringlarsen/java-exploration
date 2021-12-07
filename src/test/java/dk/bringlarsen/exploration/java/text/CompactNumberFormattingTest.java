package dk.bringlarsen.exploration.java.text;

import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JDK(version = 12, description = "Compact Number Formatting")
public class CompactNumberFormattingTest {
    private final Locale locale = new Locale("en", "US");

    @Test
    public void testShortCompactNumberFormatting() {
        NumberFormat formatter = createCompantNumberInstanceWithStyle(NumberFormat.Style.SHORT);

        assertEquals("2.59K", formatter.format(2592));
    }

    @Test
    public void testLongCompactNumberFormatting() {
        NumberFormat formatter = createCompantNumberInstanceWithStyle(NumberFormat.Style.LONG);

        assertEquals("2.59 thousand", formatter.format(2592));
    }

    private NumberFormat createCompantNumberInstanceWithStyle(NumberFormat.Style style) {
        NumberFormat formatter = NumberFormat.getCompactNumberInstance(locale, style);
        formatter.setMaximumFractionDigits(2);
        return formatter;
    }
}
