package com.epam.ld.module2.testing.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.epam.ld.module2.testing.utils.PlaceholderConstants.ADDRESSES_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.ADDRESSES_START_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.DRAFT_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.DRAFT_START_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.TEMPLATE_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.TEMPLATE_START_PLACEHOLDER;

public class ConsoleInputHelper extends InputHelperImpl {

    private final BufferedReader reader;

    public ConsoleInputHelper() {
        this.reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public ConsoleInputHelper(BufferedReader bufferedReader) {
        this.reader = bufferedReader;
    }

    @Override
    public void read() {
        System.out.println("Press Enter to use default email template OR type your own using #{...} for variables:");
        this.content += TEMPLATE_START_PLACEHOLDER + readLine() + TEMPLATE_END_PLACEHOLDER;
        System.out.println("Please input information about your email.");
        System.out.println("Addresses:");
        this.content += ADDRESSES_START_PLACEHOLDER + readLine() + ADDRESSES_END_PLACEHOLDER;
        System.out.println("Draft message:");
        this.content += DRAFT_START_PLACEHOLDER + readLine() + DRAFT_END_PLACEHOLDER;
    }

    @Override
    public void write(String addresses, String messageContent) {
        System.out.println("\nThe email was sent successfully!\n" +
                "---------------------------------------------\n" +
                "To: " + addresses + "\n" +
                messageContent + "\n" +
                "---------------------------------------------");
    }

    private String readLine() {
        String line = null;
        while (line == null) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred during entering text. Try again.");
            }
        }
        return line;
    }

}
