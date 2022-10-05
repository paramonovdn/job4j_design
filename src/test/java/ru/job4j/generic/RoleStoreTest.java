package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        Role result = roleStore.findById("1");
        assertThat(result.getProffesion()).isEqualTo("Programmer");

    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        Role result = roleStore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        roleStore.add(new Role("1", "Maxim", "Tester"));
        Role result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        roleStore.replace("1", new Role("1", "Maxim", "Tester"));
        User result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        roleStore.replace("10", new Role("10", "Maxim", "Tester"));
        Role result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        roleStore.delete("10");
        Role result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        boolean rsl = roleStore.replace("1", new Role("1", "Maxim", "Tester"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        boolean rsl = roleStore.replace("10", new Role("10", "Maxim", "Tester"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        boolean rsl = roleStore.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr", "Programmer"));
        boolean rsl = roleStore.delete("2");
        assertThat(rsl).isFalse();
    }
}
