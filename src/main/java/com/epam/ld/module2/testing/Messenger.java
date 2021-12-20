package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.InputHelper;
import com.epam.ld.module2.testing.utils.InputHelperImpl;

/**
 * The type Messenger.
 */
public class Messenger {
    private final MailServer mailServer;
    private final TemplateEngine templateEngine;
    private final InputHelperImpl inputHelper;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     * @param inputHelper    the input helper
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine,
                     InputHelperImpl inputHelper) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
        this.inputHelper = inputHelper;
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


    /**
     * Launch the messenger.
     */
    public void launch() {
        this.inputHelper.read();
        Template template = getTemplate(this.inputHelper);
        Client client = getClient(this.inputHelper);
        sendMessage(client, template);
    }

    private Template getTemplate(InputHelper inputHelper) {
        Template template = new Template();
        String customTemplate = inputHelper.getTemplate();
        if (!customTemplate.isEmpty()) {
            template.setTemplateText(customTemplate);
        }
        return template;
    }

    private Client getClient(InputHelper inputHelper) {
        return new Client(inputHelper.getAddresses(), inputHelper.getDraftMessage());
    }
}