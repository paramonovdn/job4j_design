package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whatSize() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set).hasSize(3);
    }

    @Test
    void whenAddNullBegin() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set).hasSize(3);
        assertThat(set.add(null)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.add(3)).isFalse();
    }
    @Test
    void whenAddNullMiddle() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set).hasSize(3);
        assertThat(set.add(null)).isFalse();
        assertThat(set.add(1)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNullEnd() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set).hasSize(3);
        assertThat(set.add(null)).isFalse();
        assertThat(set.add(1)).isFalse();
        assertThat(set.add(2)).isFalse();
        assertThat(set.add(3)).isTrue();
    }


}