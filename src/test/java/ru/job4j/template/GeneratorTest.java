package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
public class GeneratorTest {
    @Test
    public void whenTemplateContainsKeysAndMapHasAllKeysThenReturnString() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Sergey", "subject", "you");
        String result = generator.produce(template, args);
        String expected = "I am a Sergey, Who are you?";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenTemplateContainsKeysAndMapHasExtraKeysThenException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are you?";
        Map<String, String> args = Map.of("name", "Sergey", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateContainsKeysAndMapHasFewerKeysThenException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Sergey");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateContainsKeysAndMapIsEmptyThenException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are Who are ${subject}?";
        Map<String, String> args = Map.of();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateContainsKeysAndMapIsNullThenException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are Who are ${subject}?";
        Map<String, String> args = null;
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateWithoutKeysThenException() {
        Generator generator = new StringGenerator();
        String template = "I am a Sergey, Who are Who are you?";
        Map<String, String> args = Map.of("name", "Sergey", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateIsNullThenException() {
        Generator generator = new StringGenerator();
        String template = null;
        Map<String, String> args = Map.of("name", "Sergey", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
