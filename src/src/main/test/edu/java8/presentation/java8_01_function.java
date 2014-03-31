package edu.java8.presentation;

import edu.java8.presentation.domain.Car;
import edu.java8.presentation.domain.Limousine;
import edu.java8.presentation.utils.Java8FunctionUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static edu.java8.presentation.assertion.CarListAssertion.assertThat;
import static edu.java8.presentation.builder.CarTestBuilder.car;

public class java8_01_function {

    @Test
    public void shouldPredicateToFilterList() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return "Astra".equals(car.getModel()) ;
            }
        });
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldUseFunctionToModifyList() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        List<Limousine> workshopMagic = Java8FunctionUtils.function(cars, new Function<Car, Limousine>() {
            @Override
            public Limousine apply(Car car) {
                return new Limousine("Insignia",10, car);
            }
        });
        //then
        assertThat(workshopMagic).containsOnlyModel("Insignia").hasSize(3);
    }



}
