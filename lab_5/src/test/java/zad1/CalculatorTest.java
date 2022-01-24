package zad1;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
        System.out.println("Before");
    }

    @After
    public void tearDown(){
        calculator = null;
        System.out.println("After");
    }

    @Test
    public void testAddition() {
        assertEquals(5L, calculator.calculate(3L, 2L, OperationType.ADD));
        assertEquals(100L, calculator.calculate(75L, 25L, OperationType.ADD));
        assertEquals(4500L, calculator.calculate(1750L, 2750L, OperationType.ADD));
    }

    @Test
    public void testAddition2() {
        assertEquals(-18L, calculator.calculate(-20L, 2L, OperationType.ADD));
        assertEquals(-4L, calculator.calculate(-2L, -2L, OperationType.ADD));
    }

    @Test
    public void testSubtraction() {
        long result = calculator.calculate(6, 2, OperationType.SUBTRACT);

        assertEquals(4, result);
    }

    @Test
    public void testSubtraction2() {
        long result = calculator.calculate(100, 25, OperationType.SUBTRACT);

        assertEquals(75, result);
    }

    @Test
    public void testMultiplication() {
        long result = calculator.calculate(4, 2, OperationType.MULTIPLY);

        assertEquals(8, result);
    }

    @Test
    public void testMultiplication2() {
        long result = calculator.calculate(23, 5, OperationType.MULTIPLY);

        assertTrue(result > 100);
    }

    @Test
    public void testDivision() {
        long result = calculator.calculate(8, 2, OperationType.DIVIDE);

        assertEquals(4, result);
    }

    @Test
    public void testDivision2() {
        long result = calculator.calculate(100, 3, OperationType.DIVIDE);

        assertNotEquals(33.3, result);
    }

    @Test
    public void throwArithmeticExceptionWhenDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate(2L, 0L, OperationType.DIVIDE));
    }

    @Test
    public void throwNullPointerExcecptionWhenNullOperation() {
        assertThrows(NullPointerException.class, () -> calculator.calculate(2, 2, null));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenInvalidOperation() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(2, 2, OperationType.valueOf("test")));
    }

    @Test
    public void testPow() {
        assertEquals(8d, calculator.pow(2, 3), 0);
    }

    @Test
    public void testIfResultIsEqualOneWhenExponentIsZero() {
        assertEquals(1.0d, calculator.pow(1234, 0), 0);
    }

    @Test
    public void testIfResultIsNanWhenExponentIsNan() {
        assertEquals(Double.NaN, calculator.pow(2, Double.NaN), 0);
    }

    @Test
    public void testIfResultIsOneWhenFactorialIsZero() {
        int factorial = calculator.factorial(0);
        assertEquals(1, factorial);
    }

    @Test
    public void testFactorialCalculation() {

        int factorial = calculator.factorial(5);
        assertEquals(120, factorial);
    }

    @Test
    public void testCombinationOperations() {

        long base = calculator.calculate(2, 5, OperationType.ADD);
        long exponent = calculator.calculate(2, 2, OperationType.MULTIPLY);

        double result = calculator.pow(base, exponent);

        assertEquals(2401.0, result, 0.0);
    }

    @Test
    public void testIfNamesAreRandom() {

        String firstName1 = calculator.generateFirstName();
        String firstName2 = calculator.generateFirstName();

        assertNotEquals(firstName1, firstName2);

    }

    @Test
    public void testIfLastNamesAreRandom() {

        String lastName1 = calculator.generateLastName();
        String lastName2 = calculator.generateLastName();

        assertNotEquals(lastName1, lastName2);

    }

    @Test
    public void testMoneyOperation() {

        Money money = Money.parse("USD 23.87");

        CurrencyUnit usd = CurrencyUnit.of("USD");
        money = money.plus(Money.of(usd, 12.43d));

        money = money.minusMajor(2);

        // multiplies by 3.5 with rounding
        money = money.multipliedBy(3.5d, RoundingMode.DOWN);

        assertEquals(BigDecimal.valueOf(120.05), money.getAmount());



    }












 }
