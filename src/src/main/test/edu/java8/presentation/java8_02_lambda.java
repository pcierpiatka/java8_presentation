package edu.java8.presentation;

import edu.java8.presentation.domain.Car;
import edu.java8.presentation.utils.Java8FunctionUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static edu.java8.presentation.assertion.CarListAssertion.assertThat;
import static edu.java8.presentation.builder.CarTestBuilder.car;

public class java8_02_lambda {

    @Test
    public void shouldListBeFilteredUsingLambda_1() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when

        Predicate<Car> astra = (Car car) -> "Astra".equals(car.getModel());
        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, astra);
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldListBeFilteredUsingLambda_2() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when

        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, (Car car) -> "Astra".equals(car.getModel()));
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldListBeFilteredUsingLambda_3() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when

        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, car -> {String name = "Astra";
                                                                     return name.equals(car.getModel())
                                                                             && car.getYear() == 2011; });
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").containsOnlyCarsFromYear(2011).hasSize(1);
    }

    @Test
    public void shouldListBeFilteredUsingLambda_4() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when

        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, car -> "Astra".equals(car.getModel()));
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldListBeFilteredUsingLambda_5() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when

        List<Car> onlyAstra = Java8FunctionUtils.filter(cars, Car::isAstra);
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldSortListUsingLambda() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Corsa").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        Collections.sort(cars, (Car car1, Car car2) -> car2.getModel().compareTo(car1.getModel()));
        //then
        assertThat(cars).orderIs("Vectra","Corsa","Astra");
    }
}
