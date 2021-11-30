package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.TemplatePlaceholderException;
import com.epam.ld.module2.testing.utils.ClientDataGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateEngineTest {

    @Test
    public void shouldThrowTemplatePlaceholderExceptionWhenPlaceholderValueIsNotProvided() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Subject: #{suObject} \n From: #{addresses} \n #{greeting} " +
                "\n\n #{body} \n\n #{signature}");
        Client client = ClientDataGenerator.generateClient();

        assertThrows(TemplatePlaceholderException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void shouldReplacePlaceholders() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Subject: #{subject} \n From: #{addresses} \n #{greeting} " +
                "\n\n #{body} \n\n #{signature}");
        Client client = ClientDataGenerator.generateClient();

        String expectedMessage = "Subject: Good news! \n From: address@gmail.com \n Hello there! " +
                "\n\n Long long long text \n\n Best regards, unknown person.";
        String actualMessage = templateEngine.generateMessage(template, client);

        assertEquals(expectedMessage, actualMessage);
    }
}
