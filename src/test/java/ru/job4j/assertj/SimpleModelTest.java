package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleModelTest {
    @Test
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void  checkName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("name", 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

}