package dk.bringlarsen.exploration.java.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

public class BigDecimalComparisonTest {

    @Test
    public void comparingBigDecimalsTest() {
        BigDecimal bd1 = new BigDecimal("1.1");
        BigDecimal bd2 = new BigDecimal("1.10");

        assertEquals(0, bd1.compareTo(bd2), "the `compareTo` ignore redundant zeros");
        assertNotEquals(bd1, bd2, "the `equals` method do consider scale");
    }

    @Test
    public void presicion() {
        assertEquals("2.6", new BigDecimal("2.56").setScale(1, RoundingMode.HALF_UP).toString());
        assertEquals("2.6", new BigDecimal("2.55").setScale(1, RoundingMode.HALF_UP).toString());
        assertEquals("2.5", new BigDecimal("2.54").setScale(1, RoundingMode.HALF_UP).toString());
    }

    @Test
    public void whenRoundingDecimal_thenExpectedResult() {
        BigDecimal bd = new BigDecimal("2.5612");
    
        assertEquals("2.57", bd.round(new MathContext(2, RoundingMode.UP)).toString());
        assertEquals("2.57", bd.round(new MathContext(2, RoundingMode.CEILING)).toString());
        assertEquals("2.56", bd.round(new MathContext(2, RoundingMode.DOWN)).toString());
        assertEquals("2.56", bd.round(new MathContext(2)).toString());
    }
}
