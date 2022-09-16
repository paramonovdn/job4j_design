package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void checkparse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");

        String name = "Curt Cobain";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain the symbol \"=\"");

        String name1 = "=Curt Cobain";
        assertThatThrownBy(() -> nameLoad.parse(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name1)
                .hasMessageContaining("does not contain a key");

        String name2 = "Curt Cobain=";
        assertThatThrownBy(() -> nameLoad.parse(name2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name2)
                .hasMessageContaining("does not contain a value");

    }


}