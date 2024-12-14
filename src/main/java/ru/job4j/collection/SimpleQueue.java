package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> input = new SimpleStack<>();

    private final SimpleStack<T> output = new SimpleStack<>();

    private int inputSize;

    private int outputSize;

    public T poll() {
        if (outputSize == 0 && inputSize == 0) {
            throw new NoSuchElementException(
                    "Queue is empty"
            );
        }
        if (outputSize == 0) {
            replaceInputToOutput();
        }
        outputSize--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inputSize++;
    }

    private void replaceInputToOutput() {
        int count = inputSize;
        for (int i = 0; i < count; i++) {
            output.push(input.pop());
            inputSize--;
            outputSize++;
        }
    }
}
