package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#hibernate.connection.username")).isNull();
        assertThat(config.value("#hibernate.dialect")).isNull();
    }

    @Test
    void whenPairContainsBlankString() {
        String path = "./data/pair_with_blank_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(" ")).isNull();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenMissingKeyThenThrowsIllegalArgumentException() {
        String path = "./data/pair_with_missing_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не верный формат. В строках конфига отсутсвует ключ или значение");
    }

    @Test
    void whenMissingValueThenThrowsIllegalArgumentException() {
        String path = "./data/pair_with_missing_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не верный формат. В строках конфига отсутсвует ключ или значение");
    }

    @Test
    void whenLineContainsOnlyEqualsSingThenThrowsIllegalArgumentException() {
        String path = "./data/line_contains_only_equals_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не верный формат. В строках конфига отсутсвует ключ или значение");
    }

    @Test
    void whenLPairWithoutEqualsSignThenThrowsIllegalArgumentException() {
        String path = "./data/pair_without_equals_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не верный формат. В строках конфига пропущен знак =");
    }

    @Test
    void whenLPairWithMultipleEqualsSign() {
        String path = "./data/pair_with_multiple_equals_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver=1");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=2=");
    }

    @Test
    void whenLPairWithKeyValueWithSpacesThenTrimmedValue() {
        String path = "./data/pair_with_key_value_with_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }
}
