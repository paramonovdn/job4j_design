package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean passanger;
    private final int yearOfProduction;
    private final String brand;
    private final Engine engine;
    private final String[] options;

    public Car(boolean passanger, int yearOfProduction, String brand, Engine engine, String[] options) {
        this.passanger = passanger;
        this.yearOfProduction = yearOfProduction;
        this.brand = brand;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
               + "passanger=" + passanger
               + ", yearOfProduction=" + yearOfProduction
               + ", brand='" + brand + '\''
               + ", engine=" + engine
               + ", options=" + Arrays.toString(options)
               + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 2019, "Opel", new Engine("C30SE"),
                 new String[] {"conditioner", "2 airbag"});
        /*
        * Преобразуем объект person в json-строку.
          */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /*
        *Модифицируем json-строку в объект
        */
        final String carJson =
                "{"
                        + "\"passanger\":true,"
                        + "\"yearOfProduction\":2019,"
                        + "\"brand\":Opel,"
                        + "\"engine\":"
                        + "{"
                        + "\"engine\":\"C30SE\""
                        + "},"
                        + "\"options\":"
                        + "[\"conditioner\",\"2 airbag\"]"
                        + "}";
        final Car personMod = gson.fromJson(carJson, Car.class);
        System.out.println(personMod);
    }
}
