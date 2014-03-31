package edu.java8.presentation.domain;

import java.math.BigDecimal;

public class Limousine extends Car {

    public Limousine(String newModelName, int changeYearOf, Car car) {
        super(newModelName, car.getYear() - changeYearOf, car.getManufacturer(), new BigDecimal("5000").add(car.getPrice()));
    }
}
