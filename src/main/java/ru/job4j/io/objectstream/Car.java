package ru.job4j.io.objectstream;

import java.io.*;

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private String brand;

    private String model;

    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", year=" + year
                + '}';
    }
    public static void main(String[] args) {
        Car car = new Car("Фирма", "Модель", 2000);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/serialized.dat"));
             ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/serialized.dat"))) {
            out.writeObject(car);
            Car deserialized = (Car) in.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}