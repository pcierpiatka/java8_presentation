package edu.java8.presentation;

import edu.java8.presentation.assertion.CarListAssertion;
import edu.java8.presentation.assertion.CarMapAssertion;
import edu.java8.presentation.domain.Car;
import edu.java8.presentation.domain.Limousine;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static edu.java8.presentation.assertion.CarListAssertion.assertThat;
import static edu.java8.presentation.builder.CarTestBuilder.car;
import static java.util.stream.Collectors.toList;

public class java8_04_stream {

    @Test
    public void shouldListNotBeFilteredWhenCollectIsNotCall() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        cars.stream().filter(car -> "Astra".equals(car.getModel()));
        //then
        assertThat(cars).hasSize(3);
    }

    @Test
    public void shouldListBeFilteredUsingLambdaAndStream() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        List<Car> onlyAstra = cars.stream().filter(car -> "Astra".equals(car.getModel())).collect(toList());
        //then
        assertThat(onlyAstra).containsOnlyModel("Astra").hasSize(2);
    }

    @Test
    public void shouldMapObjectUsingStream() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        List<Limousine> limousines = cars.stream().filter(car -> car.getYear() > 2010)
                .map(car -> new Limousine("Insignia", 10, car)).collect(toList());
        //then
        CarListAssertion.assertThat(limousines).containsOnlyModel("Insignia").hasSize(2);
    }

    @Test
    public void shouldGroupUsingBuildInStreamCollector() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        Map<String, List<Car>> groupByModel = cars.stream().filter(car -> car.getYear() > 2010).collect(Collectors.groupingBy(Car::getModel));
        //then

        CarMapAssertion.assertThat(groupByModel).hasGroups("Astra", "Vectra");
    }

    @Test
    public void shouldGroupUsingCustomStreamCollector() {
        //given
        List<Car> cars  = Arrays.asList(
                car().withManufacturer("Opel").withModel("Astra").withYear(2010).withPrice("15000").build(),
                car().withManufacturer("Opel").withModel("Astra").withYear(2011).withPrice("25000").build(),
                car().withManufacturer("Opel").withModel("Vectra").withYear(2012).withPrice("35000").build()
        );
        //when
        Map<BigDecimal, String> customCollector = cars.stream()
                .collect(Collectors.toMap(Car::getPrice, car -> car.getManufacturer() + " " + car.getModel()));
        //then
        Assertions.assertThat(customCollector)
                .containsEntry(new BigDecimal("15000"), "Opel Astra")
                .containsEntry(new BigDecimal("25000"), "Opel Astra")
                .containsEntry(new BigDecimal("35000"), "Opel Vectra");
    }

}
