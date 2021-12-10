package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.TemplatePlaceholderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateEngineTest {

    @Test
    public void shouldThrowTemplatePlaceholderExceptionWhenPlaceholderValueIsNotProvided() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Subject: #{suObject} \n From: #{addresses} \n #{greeting} " +
                "\n\n #{body} \n\n #{signature}");
        Client client = generateClient();

        assertThrows(TemplatePlaceholderException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void shouldReplacePlaceholders() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template();
        Client client = generateClient();

        String expectedMessage = "Subject: Good news! \n From: address@gmail.com \n Hello there! " +
                "\n\n Long long long text \n\n Best regards, unknown person.";
        String actualMessage = templateEngine.generateMessage(template, client);

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,b,c,d,e # Subject: a From: b c Where d And e",
            "1,2,3,4,5 # Subject: 1 From: 2 3 Where 4 And 5",
            "Good news!,address@gmail.com,Hello there!,Long long text,Best regards. " +
                    "# Subject: Good news! From: address@gmail.com Hello there! Where Long long text And Best regards."},
            delimiter = '#')
    void shouldGenerateExpectedMessageUsingParameterizedTest(String data, String expectedMessage) {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Subject: #{subject} From: #{addresses} #{greeting} Where " +
                "#{body} And #{signature}");
        Client client = generateClientBasedOnData(data);
        String actualMessage = templateEngine.generateMessage(template, client);

        assertEquals(actualMessage, expectedMessage);
    }

    private Client generateClient() {
        return Client.builder()
                .subject("Good news!")
                .addresses("address@gmail.com")
                .greeting("Hello there!")
                .body("Long long long text")
                .signature("Best regards, unknown person.")
                .build();
    }

    private Client generateClientBasedOnData(String data) {
        List<String> dataList = Arrays.asList(data.split(","));
        if (dataList.size() != 5) {
            return new Client();
        }
        return Client.builder()
                .subject(dataList.get(0))
                .addresses(dataList.get(1))
                .greeting(dataList.get(2))
                .body(dataList.get(3))
                .signature(dataList.get(4))
                .build();
    }
}
