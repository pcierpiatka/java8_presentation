package edu.java8.presentation.assertion;

import edu.java8.presentation.domain.Car;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class CarListAssertion extends AbstractAssert<CarListAssertion, List<? extends Car>>{

    protected CarListAssertion(List<? extends Car> actual) {
        super(actual, CarListAssertion.class);
    }

    public static CarListAssertion assertThat(List<? extends Car> actual) {
        return new CarListAssertion(actual);
    }

    public CarListAssertion containsOnlyModel(String expected) {
        contains(car -> expected.equalsIgnoreCase(car.getModel()) ,"There should be only %s model but there are %s ", expected );
        return this;
    }

    public CarListAssertion hasSize(int expected) {
        Assertions.assertThat(actual).hasSize(expected);
        return this;
    }

    public CarListAssertion containsOnlyCarsFromYear(int expected) {
        contains(car -> expected == car.getYear() ,"There should be only cars from %s year but there are %s ", expected );
        return this;
    }

    private String carDescription(Car car) {
        return car.getManufacturer() + " " + car.getModel() + " " + car.getYear() + "  price : " + car.getPrice() ;
    }

    private CarListAssertion contains(Predicate<Car> predicate, String errorMsg, Object expected) {
        List<String> othersCar = actual.stream().filter(predicate.negate())
                .map(car -> carDescription(car))
                .collect(toList());
        Assertions.assertThat(othersCar).overridingErrorMessage(errorMsg, expected,  othersCar.toString()).isEmpty();
        return this;
    }

    public CarListAssertion orderIs(String...order) {
        int index = 0;
        for(String model : order) {
            Car car = actual.get(index);
            Assertions.assertThat(car.getModel())
                    .overridingErrorMessage("Order is incorrect. On position %s should be %s but is %s", index, model, car.getModel()).isEqualTo(model);

            index++;
        }
        return this;
    }
}
