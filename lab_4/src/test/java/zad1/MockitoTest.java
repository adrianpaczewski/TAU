package zad1;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Test
    public void test1() {
        Calculator mockCalculator = mock(Calculator.class);
        given(mockCalculator.calculate(2, 4, OperationType.ADD)).willReturn(6L);
        long result = mockCalculator.calculate(2, 4, OperationType.ADD);
        assertEquals(6L, result);

    }

    @Test
    public void test2() {
        Calculator mockCalculator = mock(Calculator.class);
        when(mockCalculator.factorial(3)).thenReturn(6);
        int result = mockCalculator.factorial(3);
        assertEquals(6, result);
    }

    @Test
    public void test3() {
        Calculator mockCalculator = mock(Calculator.class);
        when(mockCalculator.isEven(10)).thenReturn(true);
        boolean result = mockCalculator.isEven(10);
        assertTrue(result);
    }

    @Test
    public void test4() {
        FakerGenerator mockFakerGenerator = mock(FakerGenerator.class);
        when(mockFakerGenerator.generateFirstName()).thenReturn("Jan");
        String randomName = mockFakerGenerator.generateFirstName();

        assertEquals("Jan", randomName);
    }

    @Test
    public void test5() {
        FakerGenerator mockFakerGenerator = mock(FakerGenerator.class);
        when(mockFakerGenerator.generateNumber()).thenReturn(5);
        int randomNumber = mockFakerGenerator.generateNumber();

        assertEquals(5, randomNumber);
    }

    @Test
    public void test6() {
        FakerGenerator mockFakerGenerator = mock(FakerGenerator.class);
        when(mockFakerGenerator.parseMoneyAmount("USD 25")).thenReturn(BigDecimal.valueOf(25));

        BigDecimal result = mockFakerGenerator.parseMoneyAmount("USD 25");

        assertEquals(BigDecimal.valueOf(25), result);
    }

}
