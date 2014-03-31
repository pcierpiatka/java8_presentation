package edu.java8.presentation.domain;

import java.math.BigDecimal;

public class Car {

    private String model;
    private int year;
    private String manufacturer;
    private BigDecimal price;

    public Car(String model, int year, String manufacturer, BigDecimal price) {
        this.model = model;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAstra() {
        return "Astra".equals(model);
    }

    public void changePrice(String newPrice) {
        this.price = new BigDecimal(newPrice);
    }
}
