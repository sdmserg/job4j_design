package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

public class BoxTest {

    @Test
    public void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .startsWithIgnoringCase("sph")
                .endsWith("ere")
                .isEqualTo("Sphere");
    }

    @Test
    public void isThisUNKNOWN() {
        Box box = new Box(12, 4);
        String name = box.whatThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("OBJECT")
                .contains("Unknown", "object")
                .doesNotContain("Sphere")
                .startsWith("Un")
                .startsWithIgnoringCase("u")
                .endsWith("ect")
                .isEqualTo("Unknown object");
    }

    @Test
    public void whenNumberOfVerticesIs4() {
        Box box = new Box(4, 2);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    public void whenNumberOfVerticesIsMinus1() {
        Box box = new Box(12, 4);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isNegative()
                .isOdd()
                .isGreaterThan(-2)
                .isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    public void wheIsExistIsTrue() {
        Box box = new Box(0, 4);
        boolean isExist = box.isExists();
        assertThat(isExist).isNotNull()
                .isEqualTo(true)
                .isTrue();
    }

    @Test
    public void whenIsNotExists() {
        Box box = new Box(12, 4);
        boolean isExist = box.isExists();
        assertThat(isExist).isNotNull()
                .isEqualTo(false)
                .isFalse();
    }

    @Test
    public void whenAreaIs201Dot06() {
        Box box = new Box(0, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(201.06, withPrecision(0.005d))
                .isCloseTo(201.00d, withPrecision(0.07d))
                .isCloseTo(201.00d, Percentage.withPercentage(1.0d))
                .isGreaterThan(201.00d)
                .isLessThan(201.07d);
    }

    @Test
    public void whenAreaIs0() {
        Box box = new Box(12, 4);
        double area = box.getArea();
        assertThat(area).isZero()
                .isEqualTo(0, withPrecision(0.005d))
                .isCloseTo(0, Percentage.withPercentage(1.0d))
                .isGreaterThan(-1)
                .isLessThan(1);
    }
}
