package edu.java8.presentation.builder;

import edu.java8.presentation.domain.Car;

import java.math.BigDecimal;

public class CarTestBuilder {

    private String model;
    private int year;
    private String manufacturer;
    private BigDecimal price;

    protected CarTestBuilder(){};

    public static CarTestBuilder car() {
        return new CarTestBuilder();
    }

    public CarTestBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarTestBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public CarTestBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public CarTestBuilder withPrice(String price) {
        this.price = new BigDecimal(price);
        return this;
    }

    public CarTestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Car build() {
        return new Car(model, year, manufacturer, price);
    }
}
