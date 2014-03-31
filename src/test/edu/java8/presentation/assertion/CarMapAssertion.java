package edu.java8.presentation.assertion;

import edu.java8.presentation.domain.Car;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

public class CarMapAssertion extends AbstractAssert<CarMapAssertion, Map<String, List<Car>>> {

    protected CarMapAssertion(Map<String, List<Car>> actual) {
        super(actual, CarMapAssertion.class);
    }


    public static CarMapAssertion assertThat(Map<String, List<Car>> actual) {
        return new CarMapAssertion(actual);
    }

    public CarMapAssertion hasGroups(String...groups) {
        for(String group : groups) {
            List<Car> cars = actual.get(group);
            Assertions.assertThat(cars).isNotEmpty();
        }
        return this;
    }

}
