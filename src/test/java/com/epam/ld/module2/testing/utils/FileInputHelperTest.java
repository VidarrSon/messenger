package com.epam.ld.module2.testing.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileInputHelperTest {

    // @Rule is not supported in jUnit 5
    @TempDir
    public File tempDir;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @EnabledIf("isDataFileExists")
    public void shouldReadContentFromFile() {
        FileInputHelper fileInputHelper = new FileInputHelper(
                Arrays.asList("src/test/resources/data.txt", "target"));
        fileInputHelper.read();

        assertNotNull(fileInputHelper.content);
    }

    @Test
    public void shouldWriteContentToFile() {
        File tempDirFile = new File(tempDir, "message.txt");
        FileInputHelper fileInputHelper = new FileInputHelper(
                Arrays.asList("source", tempDirFile.getPath()));
        fileInputHelper.write("my_address@gmail.com", "message content here");

        assertTrue(tempDirFile.exists());
    }

    public boolean isDataFileExists() {
        return new File("src/test/resources/data.txt").exists();
    }
}
