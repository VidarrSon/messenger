package com.epam.ld.module2.testing.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String line = null;
        while (line == null) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                writeMessage("An error occurred during entering text. Try again.");
            }
        }
        return line;
    }
}
