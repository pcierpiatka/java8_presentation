package edu.java8.presentation.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommissionService {

    private final List<Car> cars;
    private final Map<String, String> descriptions;

    public CommissionService(List<Car> cars, Map<String, String> descriptions) {
        this.cars = cars;
        this.descriptions = descriptions;
    }

    private Car findCar(BigDecimal budget, int year) {
        Car searchCar = null;
        for (Car car : cars) {
            if (car.getPrice().compareTo(budget) == 0
                    && car.getYear() == year) {
                return car;
            }
        }
        return searchCar;
    }

    public String offer(String model, BigDecimal budget, int year) {
            final Car car = findCar(budget, year);
            if (car != null) {
                if (car.getModel().equals(model)) {
                    final String description = findDecryption(car);
                    if (description != null && !description.isEmpty()) {
                        return "Only for You! " + description;
                    } else {
                        return null;
                    }
                }
            }
            return null;
        }

    private String findDecryption(Car car) {
        return descriptions.get(car.getManufacturer() +" " +car.getModel());
    }

    public String optionalOffer(String model, BigDecimal budget, int year) {
        final Optional<Car> car = safeFind(budget, year);
        return car.filter(c -> c.getModel().equals(model))
                .flatMap(this::safeFindDecryption)
                .filter(desc -> !desc.isEmpty())
                .map(decs->"Only for You! " + decs).orElse("Uppa");
    }

    private Optional<Car> safeFind(BigDecimal budget, int year) {
        return cars.stream().filter(car -> car.getPrice().compareTo(budget) == 0 && car.getYear() == year).findAny();
    }
    private Optional<String> safeFindDecryption(Car car) {
        return Optional.of(descriptions.get(car.getManufacturer() + " " + car.getModel()));
    }

}
