package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {

    public static void main(String[] args) {
        /*
        * ������ �� ������� ������ � ������� ������ ByteArrayInputStream
         */
        byte[] bytes = new byte[] {'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        /*
        *������������� ������� ������������ ByteArrayInputStream(byte[] buf, int offset, int length)
        * � ���� ����������� ���������� ��� ��������� offset (�������, � ������� �������� ����������)
        * � length (������� ������ ����� �������)
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
        *���������� ������ ������� ������ �� ���������� ���� ������� outStream. �������� ��������, ��� ������
        * ������ write() �� ���������� ����� writeBytes(). ����� write() ����� ��������� ����������
        * �����-������ (IOException), � ��� ��� � ������ ������ ������ ������� �� ���������� ���� ������� outStream
        * ���������� ��� ������� ������� �����-������, �� � Java 11 � ������ ByteArrayOutputStream ��������
        * ����� writeBytes(), ������� ���������� ������ write(). �� ����� ��� �� ���������� ��������� ����������,
        * ������ ��� ��� ������������� �� ����� ����������� ���� ��� � ���� try-catch (�� ������� ����������).
         */
        System.out.println();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outStream.writeBytes(bytes2);       /* * ������ ������� outStream ���� ���������� ���� protected byte buf[] */
        System.out.println(outStream);      /* * ������ � ���� ������������ ����� ������ */

        /*
        *��������� ������ ������ ������� �������� ������. ��� ������ � ��������� ����� ������� (��������)
        *  ��� ������ � ����� ������ ����.
         */
        byte[] byteArray = outStream.toByteArray();
        /*
        *� ��� ���� ������ - �������� ���������� ������ ���� � ������ �����. ��������, �������� � ����.
        * ��� ����� ����� ������������ ����� writeTo().
         */
        try (FileOutputStream fileOutput = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}