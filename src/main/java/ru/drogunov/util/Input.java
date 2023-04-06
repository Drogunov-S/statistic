package ru.drogunov.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Input {

    private final BufferedReader bufferedReader;

    public Input(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Errors with input", e);
        }
    }
}
