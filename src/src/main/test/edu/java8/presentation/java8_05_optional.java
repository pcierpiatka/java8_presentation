package edu.java8.presentation;

import edu.java8.presentation.domain.Car;
import edu.java8.presentation.domain.CommissionService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static edu.java8.presentation.builder.CarTestBuilder.car;
import static edu.java8.presentation.builder.MapTestBuilder.map;
import static org.assertj.core.api.Assertions.assertThat;

public class java8_05_optional {

    @Test
    public void shouldFindCarDescription() {
        //given
        List<Car> cars = Arrays.asList(car().withModel("Astra").withManufacturer("Opel").withPrice("1000").withYear(2013).build(),
                car().withModel("Vectra").withManufacturer("Opel").withPrice("2000").withYear(2012).build()
        );
        Map<String, String> descriptions = map().with("Opel Astra", "Some good description").build();
        CommissionService commissionService = new CommissionService(cars, descriptions);

        //when
        String description = commissionService.offer("Astra", new BigDecimal("1000"), 2013);
        //then
        assertThat(description).isEqualTo("Only for You! Some good description");
    }

    @Test
    public void shouldFindCarDescriptionOptional() {
        //given
        List<Car> cars = Arrays.asList(car().withModel("Astra").withManufacturer("Opel").withPrice("1000").withYear(2013).build(),
                car().withModel("Vectra").withManufacturer("Opel").withPrice("2000").withYear(2012).build()
        );
        Map<String, String> descriptions = map().with("Opel Astra", "Some good description").build();
        CommissionService commissionService = new CommissionService(cars, descriptions);

        //when
        String description = commissionService.optionalOffer("Astra", new BigDecimal("1000"), 2013);
        //then
        assertThat(description).isEqualTo("Only for You! Some good description");
    }

    @Test
    public void shouldNotFindCarDescriptionOptional() {
        //given
        List<Car> cars = Arrays.asList(car().withModel("Astra").withManufacturer("Opel").withPrice("1000").withYear(2013).build(),
                car().withModel("Vectra").withManufacturer("Opel").withPrice("2000").withYear(2012).build()
        );
        Map<String, String> descriptions = map().with("Opel Astra", "Some good description").build();
        CommissionService commissionService = new CommissionService(cars, descriptions);

        //when
        String description = commissionService.optionalOffer("Astra", new BigDecimal("1000"), 2014);
        //then
        assertThat(description).isEqualTo("Uppa");
    }
}