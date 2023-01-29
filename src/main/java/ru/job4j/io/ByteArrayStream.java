package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {

    public static void main(String[] args) {
        /*
        * чтение из массива байтов с помощью класса ByteArrayInputStream
         */
        byte[] bytes = new byte[] {'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        /*
        *использование другого конструктора ByteArrayInputStream(byte[] buf, int offset, int length)
        * в этот конструктор передаютс€ ещЄ параметры offset (позици€, с которой начнетс€ считывание)
        * и length (сколько байтов нужно считать)
         */
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = byteStream2.read()) != -1) {
            System.out.print((char) data1);
        }
        /*
        *происходит запись массива байтов во внутреннее поле объекта outStream. ќбратите внимание, что вместо
        * метода write() мы используем метод writeBytes(). ћетод write() может выбросить исключение
        * ввода-вывода (IOException), а так как в данном случае запись массива во внутреннее поле объекта outStream
        * происходит без участи€ системы ввода-вывода, то в Java 11 в классе ByteArrayOutputStream по€вилс€
        * метод writeBytes(), который аналогичен методу write(). ќн точно так же записывает указанное содержимое,
        * только при его использовании не нужно оборачивать этот код в блок try-catch (не бросает исключение).
         */
        System.out.println();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outStream.writeBytes(bytes2);       /* * внутри объекта outStream есть внутреннее поле protected byte buf[] */
        System.out.println(outStream);      /* * именно в него записываетс€ поток байтов */

        /*
        *—читанные данные внутри объекта изменить нельз€. ƒл€ чтени€ и изменени€ можно вывести (записать)
        *  эти данные в новый массив байт.
         */
        byte[] byteArray = outStream.toByteArray();
        /*
        *» еще один способ - передать записанный массив байт в другой поток. Ќапример, записать в файл.
        * ƒл€ этого нужно использовать метод writeTo().
         */
        try (FileOutputStream fileOutput = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}