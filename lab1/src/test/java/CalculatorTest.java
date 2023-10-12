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

    @Test
    void ShouldReturnSeventyFor5Elements()
    {
        assertEquals(70,StringCalculator.add("10,20,30,5,5"));
    }
    @Test
    void ShouldReturnSeventyFor7Elements()
    {
        assertEquals(70,StringCalculator.add("20,20,30,0,0,0,0"));
    }
    @Test
    void ShouldReturnTwentyThreeFor7ElementsWithNOrComa()
    {
        assertEquals(23,StringCalculator.add("2,10\n6\n0,1,0,4"));
    }

    @Test
    void ShouldReturnSixWithCustomDelimiter()
    {
        assertEquals(6,StringCalculator.add("//;\n1;2;3"));
    }


}
