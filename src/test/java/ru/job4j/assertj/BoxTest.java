package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    Box box = new Box(0, 10);
    Box box1 = new Box(4, 10);

    @Test
    void whatsThis() {
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }
    @Test
    void whatsThis1() {
        String name1 = box1.whatsThis();
        assertThat(name1).isEqualTo("Tetrahedron");
    }
    @Test
    void getNumberOfVertices() {
        Box box = new Box(0, 10);
        Integer vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
    }
    @Test
    void getNumberOfVertices1() {
        Integer vertex1 = box1.getNumberOfVertices();
        assertThat(vertex1).isEqualTo(4);
    }
    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean isexist = box.isExist();
        assertThat(isexist).isTrue();
    }
    @Test
    void isExist1() {
        boolean isexist1 = box1.isExist();
        assertThat(isexist1).isTrue();
    }
    @Test
    void getArea() {
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63d, withPrecision(0.01d));
    }
    @Test
    void getArea1() {
        double area1 = box1.getArea();
        assertThat(area1).isEqualTo(173.20d,withPrecision(0.01d));

    }
}