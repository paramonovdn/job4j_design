package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean passanger;
    @XmlAttribute
    private int yearOfProduction;
    @XmlAttribute
    private String brand;

    private Engine engine;
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() { }

    public Car(boolean passanger, int yearOfProduction, String brand, Engine engine, String[] options) {
        this.passanger = passanger;
        this.yearOfProduction = yearOfProduction;
        this.brand = brand;
        this.engine = engine;
        this.options = options;
    }

    public boolean isPassanger() {
        return passanger;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public String getBrand() {
        return brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOptions() {
        return options;
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

        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"model\":\"C30SE\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("conditioner");
        list.add("2 airbag");
        JSONArray jsonOptions = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(true, 2019, "Opel", new Engine("C30SE"),
                new String[] {"heated seats", "no airbag"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("passanger", car.isPassanger());
        jsonObject.put("yearOfProduction", car.getYearOfProduction());
        jsonObject.put("brand", car.getBrand());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("options", jsonOptions);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
