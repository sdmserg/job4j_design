package ru.job4j.assertj;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.data.Index;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .hasSize(5)
                .contains("five", "second")
                .contains("four", Index.atIndex(3))
                .containsAnyOf("six", "ten", "five")
                .doesNotContain("eleven", "zero")
                .containsSequence("three", "four")
                .doesNotContainSequence("first", "three")
                .startsWith("first", "second", "three")
                .endsWith("four", "five")
                .allSatisfy(str -> {
                    assertThat(str).isNotEmpty();
                    assertThat(str).isNotBlank();
                })
                .allMatch(str -> str.length() > 3)
                .element(3).isEqualTo("four");
    }

    @Test
    public void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "four", "second", "five", "first", "three", "four", "five", "second");
        assertThat(set).isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains("first", "five")
                .containsAnyOf("six", "four", "seven")
                .doesNotContain("ten", "eleven")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .allSatisfy(str -> {
                    assertThat(str).isNotNull();
                    assertThat(str).isNotBlank();
                })
                .allMatch(str -> str.length() < 10)
                .anyMatch(str -> str.contains("sec"))
                .noneMatch(str -> str.indexOf('a') == 0);
    }

    @Test
    public void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                "first", "four",
                "second", "five",
                "first", "three",
                "four", "five",
                "second");
        assertThat(map).isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .containsKeys("first", "four", "second")
                .containsValues(0, 1, 2, 3, 5)
                .doesNotContainKey("zero")
                .doesNotContainValue(4)
                .containsEntry("first", 0)
                .containsEntry("second", 2)
                .doesNotContainEntry("first", 4);
    }
}
