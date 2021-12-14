package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.TemplatePlaceholderException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Set<String> templatePlaceholders = new HashSet<>();
        String generatedMessage = template.getTemplateText();

        Pattern pattern = Pattern.compile("\\#\\{.*?\\}");
        Matcher matcher = pattern.matcher(template.getTemplateText());
        while (matcher.find()) {
            templatePlaceholders.add(matcher.group());
        }

        boolean isDraftMessageContainsAllTemplatePlaceholders = templatePlaceholders.stream()
                .allMatch(client.getDraftMessage()::contains);
        if (!isDraftMessageContainsAllTemplatePlaceholders) {
            throw new TemplatePlaceholderException("Placeholder is missing!");
        }

        String previousPlaceholder = "";
        int ignoredPlaceholderStartIndex = -1;
        int valueStartIndex = 0;
        int valueEndIndex;
        String draftMessage = client.getDraftMessage();
        matcher = pattern.matcher(draftMessage);
        while (matcher.find()) {
            if (ignoredPlaceholderStartIndex != -1) {
                String substringToReplace = draftMessage.substring(
                        ignoredPlaceholderStartIndex, draftMessage.indexOf(matcher.group()));
                draftMessage = draftMessage
                        .replace(substringToReplace, "");
                ignoredPlaceholderStartIndex = -1;
            }
            if (templatePlaceholders.contains(matcher.group())) {
                valueEndIndex = draftMessage.indexOf(matcher.group());
                if (valueEndIndex != 0 && !previousPlaceholder.isEmpty()) {
                    generatedMessage = generatedMessage.replace(previousPlaceholder,
                            draftMessage.substring(valueStartIndex, valueEndIndex));
                }
                previousPlaceholder = matcher.group();
                valueStartIndex = valueEndIndex + matcher.group().length();
            } else {
                ignoredPlaceholderStartIndex = draftMessage.indexOf(matcher.group());
            }
        }
        valueEndIndex = draftMessage.length();
        generatedMessage = generatedMessage.replace(previousPlaceholder,
                    draftMessage.substring(valueStartIndex, valueEndIndex));

        return generatedMessage;
    }
}
