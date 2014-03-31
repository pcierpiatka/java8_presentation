package edu.java8.presentation.assertion;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

/**
 * Created by pawelc on 31/03/14.
 */
public class DateAssertion extends AbstractAssert<DateAssertion, LocalDateTime> {

    protected DateAssertion(LocalDateTime actual) {
        super(actual, DateAssertion.class);
    }

    public static DateAssertion assertThat(LocalDateTime actual) {
        return new DateAssertion(actual);
    }

    public DateAssertion isYear(int expected) {
        Assertions.assertThat(actual.getYear()).isEqualTo(expected);
        return this;
    }

    public DateAssertion isMonth(int expected) {
        Assertions.assertThat(actual.getMonth()).isEqualTo(expected);
        return this;
    }

    public DateAssertion isDay(int expected) {
        Assertions.assertThat(actual.getDayOfMonth()).isEqualTo(expected);
        return this;
    }

    public DateAssertion isHour(int expected) {
        Assertions.assertThat(actual.getHour()).isEqualTo(expected);
        return this;
    }

    public DateAssertion isMinute(int expected) {
        Assertions.assertThat(actual.getMinute()).isEqualTo(expected);
        return this;
    }
}
