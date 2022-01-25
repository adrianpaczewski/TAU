package zad1;

import com.github.javafaker.Faker;
import org.joda.money.Money;

import java.math.BigDecimal;

public class FakerGenerator {

    public String generateFirstName() {
        return new Faker().name().firstName();
    }

    public String generateLastName() {
        return new Faker().name().lastName();
    }

    public int generateNumber() {
        return new Faker().number().randomDigit();
    }

    public BigDecimal parseMoneyAmount(String money) {
        return Money.parse(money).getAmount();
    }
}
