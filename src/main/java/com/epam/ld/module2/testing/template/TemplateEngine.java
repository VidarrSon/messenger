package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.TemplatePlaceholderException;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {

        if (!template.getTemplateText().contains("#{subject}")
                || !template.getTemplateText().contains("#{addresses}")
                || !template.getTemplateText().contains("#{greeting}")
                || !template.getTemplateText().contains("#{body}")
                || !template.getTemplateText().contains("#{signature}")) {
            throw new TemplatePlaceholderException("Placeholder is missing!");
        }

        return template.getTemplateText()
                .replace("#{subject}", client.getSubject())
                .replace("#{addresses}", client.getAddresses())
                .replace("#{greeting}", client.getGreeting())
                .replace("#{body}", client.getBody())
                .replace("#{signature}", client.getSignature());
    }
}
