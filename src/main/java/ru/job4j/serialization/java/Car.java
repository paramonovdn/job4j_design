package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
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

    public static void main(String[] args) throws Exception {
        Car car = new Car(true, 2019, "Opel", new Engine("C30SE"),
                new String[] {"conditioner", "2 airbag"});
        /*
        *Получаем контекст для доступа к АПИ
        */
        JAXBContext context = JAXBContext.newInstance(Car.class);
        /*
         *Создаем сериализатор
         */
        Marshaller marshaller = context.createMarshaller();
        /*
        *Указываем, что нам нужно форматирование
        */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /*
            *Сериализуем
            */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /*
        *Для десериализации нам нужно создать десериализатор
        */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /*
            *десериализуем
            */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
