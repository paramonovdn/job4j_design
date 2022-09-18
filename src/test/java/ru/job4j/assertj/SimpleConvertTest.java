package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    SimpleConvert simpleConvert = new SimpleConvert();
    @Test
    void checkArray() {
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }
    @Test
    void checkList() {
        List<String> list = simpleConvert.toList("Petr", "Igor", "Oleg", "John", "Petr");
        assertThat(list).hasSize(5)
                .contains("Petr")
                .contains("Petr", Index.atIndex(0))
                .containsAnyOf("Igor", "Oleg", "Jhon")
                .doesNotContain("Jhon", Index.atIndex(2));

    }
    @Test
    void checkSet() {
        Set<String> set = simpleConvert.toSet("Petrov", "Ivanov", "Sidorov", "Kirienko", "Lukashenko");
        assertThat(set).hasSize(5)
                .contains("Petrov")
                .contains("Ivanov")
                .contains("Sidorov")
                .contains("Kirienko")
                .contains("Lukashenko")
                .containsExactlyInAnyOrder("Petrov", "Ivanov", "Sidorov", "Kirienko", "Lukashenko");
    }


    @Test
    void checkMap() {
        Map<String, Integer> map = simpleConvert.toMap("Petrov", "Ivanov", "Sidorov", "Kirienko", "Lukashenko");
        assertThat(map).hasSize(5)
                .containsKeys("Petrov", "Ivanov", "Sidorov", "Kirienko", "Lukashenko")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKey("Putin")
                .doesNotContainValue(6)
                .containsEntry("Lukashenko", 4);
    }
}
