import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void shouldReturnExceptionForWrongDelimiterStruct() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {StringCalculator.add("1,2,3\n,6,5");});
        assertEquals("Unsupportable string input (it shouldn't contains structures (,\\n) or ('\\n',)", exception.getMessage());
    }

    @Test
    void ShouldReturnSixWithCustomDelimiter()
    {
        assertEquals(6,StringCalculator.add("//;\n1;2;3"));
    }
    @Test
    void shouldReturnExceptionWithNegatives() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {StringCalculator.add("1,2,3,-6,-5");});
        assertEquals("Negatives not allowed: [-6, -5]", exception.getMessage());
    }
    @Test
    void shouldReturnExceptionWithNegativesForCustomDelimiter() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {StringCalculator.add("//;\n1;2;3;-6;-5");});
        assertEquals("Negatives not allowed: [-6, -5]", exception.getMessage());
    }
    @Test
    void ShouldReturnSumOfNumsLess1001()
    {
        assertEquals(1999,StringCalculator.add("1000,999,1001"));
    }
    @Test
    void ShouldReturnSumOfNumsLess1001WithCustomDelimiter()
    {
        assertEquals(2008,StringCalculator.add("//g\n1000g999g1001g2g3g4"));
    }

}

