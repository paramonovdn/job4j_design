package ru.job4j.collection;

import java.util.Objects;

public class Programmer {

    private final String name;
    private final byte age;
    private final String position;
    private final int salary;

    protected Programmer(String name, byte age, String position, int salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Programmer that = (Programmer) o;
        return age == that.age && salary == that.salary && name.equals(that.name) && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        /**
        * return Objects.hash(name, age, position, salary);
        */
        int result = name.hashCode();
        result = 31 * result + Byte.hashCode(age);
        result = 31 * result + position.hashCode();
        result = 31 * result + Integer.hashCode(salary);
        return result;
    }
/**
*    @Override
*    public boolean equals(Object o) {
*        if (this == o) return true;
*        if (o == null || getClass() != o.getClass()) return false;
*
*        Programmer that = (Programmer) o;
*
*        if (salary != that.salary) return false;
*        return Objects.equals(position, that.position);
*    }
*
*   @Override
*    public int hashCode() {
*        int result = position != null ? position.hashCode() : 0;
*        result = 31 * result + salary;
*        return result;
*    }
*/
}
