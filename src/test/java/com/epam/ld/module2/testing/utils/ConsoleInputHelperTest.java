package com.epam.ld.module2.testing.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ConsoleInputHelperTest {

    @Mock
    private BufferedReader bufferedReader;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contentShouldBeNonEmptyAfterReadCall() throws IOException {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper(bufferedReader);
        when(bufferedReader.readLine()).thenReturn("test string");
        consoleInputHelper.read();

        assertNotNull(consoleInputHelper.content);
        assertNotNull(consoleInputHelper.getTemplate());
        assertNotNull(consoleInputHelper.getAddresses());
        assertNotNull(consoleInputHelper.getDraftMessage());
    }

}
