package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.exception.ReadFromFileException;
import com.epam.ld.module2.testing.exception.WriteToFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class FileInputHelper extends InputHelperImpl {

    private final List<String> inputOutputFileNames;

    public FileInputHelper(List<String> args) {
        this.inputOutputFileNames = args;
    }

    @Override
    public void read() {
        StringBuilder fileContent = new StringBuilder();
        try (Scanner scanner = new Scanner (new File(inputOutputFileNames.get(0)), "UTF-8")) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new ReadFromFileException("File to read not found!");
        }
        this.content = fileContent.toString();
    }

    @Override
    public void write(String addresses, String messageContent) {
        String message = "To: " + addresses + "\n" + messageContent;
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(inputOutputFileNames.get(1)), StandardCharsets.UTF_8)){
            writer.write(message);
        } catch (IOException e) {
            throw new WriteToFileException("The file was not written!");
        }
    }

}
