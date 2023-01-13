package ru.job4j.io;

import org.junit.jupiter.api.Test;
import ru.job4j.assertj.SimpleModel;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comments_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithDoubleEquallySimbol() {
        String path = "./data/pair_with_double_equally_simbol.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=1");
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio=");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairOnlyEqually() {
        String path = "./data/pair_with_only_equally_simbol.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutEquallySimbol() {
        String path = "./data/pair_without_equally_simbol.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_equally_simbol.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutValues() {
        String path = "./data/pair_without_equally_simbol.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }


}