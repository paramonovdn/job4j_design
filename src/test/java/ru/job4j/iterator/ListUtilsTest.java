package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> remoteElements;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, x -> x % 3 == 0);
        assertThat(input).hasSize(1).containsSequence(1);
    }
    @Test
    void whenReplaceIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.replaceIf(input, x -> x % 1 == 0, 3);
        assertThat(input).hasSize(3).containsSequence(3, 3, 3);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        remoteElements = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(input, remoteElements);
        assertThat(input).hasSize(3).containsSequence(1, 2, 5);
    }

}