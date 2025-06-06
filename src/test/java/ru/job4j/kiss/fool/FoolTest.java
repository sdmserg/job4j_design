package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoolTest {

    @Test
    public void whenNumIs15ThenFizzBUzz() {
        var answer = Fool.getMove(15);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs30ThenFizzBUzz() {
        var answer = Fool.getMove(30);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs45ThenFizzBUzz() {
        var answer = Fool.getMove(45);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs60ThenFizzBUzz() {
        var answer = Fool.getMove(60);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs75ThenFizzBUzz() {
        var answer = Fool.getMove(75);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs90ThenFizzBUzz() {
        var answer = Fool.getMove(90);
        var expectedAnswer = "FizzBuzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs3ThenFizz() {
        var answer = Fool.getMove(3);
        var expectedAnswer = "Fizz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs21ThenFizz() {
        var answer = Fool.getMove(21);
        var expectedAnswer = "Fizz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs51ThenFizz() {
        var answer = Fool.getMove(51);
        var expectedAnswer = "Fizz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs99ThenFizz() {
        var answer = Fool.getMove(99);
        var expectedAnswer = "Fizz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs5ThenFizz() {
        var answer = Fool.getMove(5);
        var expectedAnswer = "Buzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs10ThenFizz() {
        var answer = Fool.getMove(10);
        var expectedAnswer = "Buzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs55ThenFizz() {
        var answer = Fool.getMove(55);
        var expectedAnswer = "Buzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs85ThenFizz() {
        var answer = Fool.getMove(85);
        var expectedAnswer = "Buzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs95ThenFizz() {
        var answer = Fool.getMove(95);
        var expectedAnswer = "Buzz";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs7Then7() {
        var answer = Fool.getMove(7);
        var expectedAnswer = "7";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs41Then41() {
        var answer = Fool.getMove(41);
        var expectedAnswer = "41";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs53Then41() {
        var answer = Fool.getMove(53);
        var expectedAnswer = "53";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs77Then41() {
        var answer = Fool.getMove(77);
        var expectedAnswer = "77";
        assertThat(answer).isEqualTo(expectedAnswer);
    }

    @Test
    public void whenNumIs98Then98() {
        var answer = Fool.getMove(98);
        var expectedAnswer = "98";
        assertThat(answer).isEqualTo(expectedAnswer);
    }
}
