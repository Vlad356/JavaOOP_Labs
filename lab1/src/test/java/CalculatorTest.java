import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest
{
    @Test
    void ShouldReturnZero()
    {
        assertEquals(0,StringCalculator.add(""));
    }
    @Test
    void ShouldReturnOne()
    {
        assertEquals(1,StringCalculator.add("1"));
    }
    @Test
    void ShouldReturnThree()
    {
        assertEquals(3,StringCalculator.add("1,2"));
    }

}