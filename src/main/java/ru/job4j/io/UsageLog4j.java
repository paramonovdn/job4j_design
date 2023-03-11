package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       byte anyByte = 127;
       short anyShort = 32767;
       int anyInt = 2147483647;
       long anyLong = 654654654l;
       float anyFloat = 5465468746868468f;
       double anyDooble = 6546546.54d;
       char anyChar = 'a';
       boolean anyBoolean = true;
       LOG.debug("We have 8 examples of primitive variables: byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}," +
               " char: {}, boolean: {}.", anyByte, anyShort, anyInt, anyLong, anyFloat, anyDooble, anyChar, anyBoolean);
    }
}