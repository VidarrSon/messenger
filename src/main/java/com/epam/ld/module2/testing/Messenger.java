package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.ConsoleHelper;

import java.util.List;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public void sendMessage(Client client, Template template) {
        String messageContent =
            templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), messageContent);
    }

    public void runConsoleMode() {
        Template template = createTemplate();
        Client client = createClient();
        sendMessage(client, template);
    }

    public void runFileMode(List<String> args) {

    }

    private Template createTemplate() {
        Template template = new Template();
        ConsoleHelper.writeMessage("Type anything if you want to use default email template " +
                "OR type 'no' to enter your own:");
        String templateAnswer = ConsoleHelper.readString();
        if (templateAnswer.equals("no")) {
            ConsoleHelper.writeMessage("Please input a new template using #{...} for variables:");
            template.setTemplateText(ConsoleHelper.readString());
        }
        return template;
    }

    private Client createClient() {
        Client client = new Client();

        ConsoleHelper.writeMessage("Please input information about your email.");
        ConsoleHelper.writeMessage("Addresses:");
        client.setAddresses(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Draft message:");
        client.setDraftMessage(ConsoleHelper.readString());

        return client;
    }
}