package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArrayItTest {

    @Test
    public void whenMultiCallHasNextTherTrue() {
        ArrayIt iterator = new ArrayIt(
                new int[] {1, 2, 3, 4, 5}
        );
        boolean result = iterator.hasNext();
        assertThat(result).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        ArrayIt iterator = new ArrayIt(
                new int[] {1, 2, 3, 4, 5}
        );
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }
}
