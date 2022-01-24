package zad1;

import com.github.javafaker.Faker;

public class Calculator {

    public long calculate(long a, long b, OperationType operationType) {

        return switch (operationType) {

            // performs addition between numbers
            case ADD -> a + b;

            // performs subtraction between numbers
            case SUBTRACT -> a - b;

            // performs multiplication between numbers
            case MULTIPLY -> a * b;

            // performs division between numbers
            case DIVIDE -> a / b;
            default -> throw new IllegalStateException("Invalid operation: " + operationType);
        };

    }

    public int factorial(int n) {
        int iloczyn = 1;
        for (int i=1; i<=n; i++) {
            iloczyn *= i;
        }
        return iloczyn;
    }

    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public String generateFirstName() {
        return new Faker().name().firstName();
    }

    public String generateLastName() {
        return new Faker().name().lastName();
    }

}
