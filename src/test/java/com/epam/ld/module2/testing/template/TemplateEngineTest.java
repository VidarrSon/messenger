package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.TemplatePlaceholderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateEngineTest {

    @Test
    public void shouldThrowTemplatePlaceholderExceptionWhenPlaceholderValueIsNotProvided() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Subject: #{suObject} \n #{greeting} \n #{body} \n #{signature}");
        Client client = generateClient();

        assertThrows(TemplatePlaceholderException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void shouldReplacePlaceholders() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template();
        Client client = generateClient();
        String expectedMessage = "Subject: IMPORTANT INFORMATION!\n\nGood morning my best friend!\n\n" +
                "Congratulations! You just received a payment! Please send me your personal data to withdraw it.\n\n" +
                "Best regards, your buddy! (not a crook)";
        String actualMessage = templateEngine.generateMessage(template, client);

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @CsvSource(value = {"ignored text#{subject}1#{greeting}2#{body}3#{signature}4 / 1234",
            " #{ignored_placeholder}ignored text#{subject}1#{greeting}2#{body}3#{signature}4 / 1234",
            " #{subject}1#{greeting}2#{body}3#{signature}4 / 1234"},
            delimiter = '/')
    void shouldGenerateExpectedMessageUsingParameterizedTest(String draftMessage, String expectedMessage) {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("#{subject}#{greeting}#{body}#{signature}");
        Client client = new Client("address_to_send@gmail.com", draftMessage);
        String actualMessage = templateEngine.generateMessage(template, client);

        assertEquals(actualMessage, expectedMessage);
    }

    private Client generateClient() {
            return Client.builder()
                    .addresses("corporate_email@epam.com")
                    .draftMessage("#{subject}IMPORTANT INFORMATION!#{greeting}Good morning my best friend!" +
                            "#{body}Congratulations! You just received a payment! " +
                            "Please send me your personal data to withdraw it." +
                            "#{signature}Best regards, your buddy! (not a crook)")
                    .build();
    }

}
