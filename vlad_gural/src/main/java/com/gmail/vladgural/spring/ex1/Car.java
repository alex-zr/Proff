package com.gmail.vladgural.spring.ex1;

public class Car {
    private String model;
    private int year;
    private Driver driver;

    public Car() {
    }

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", " + driver +
                '}';
    }
}

