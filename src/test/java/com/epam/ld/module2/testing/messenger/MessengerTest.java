package com.epam.ld.module2.testing.messenger;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;
import com.epam.ld.module2.testing.Messenger;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.InputHelperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessengerTest {

    @Mock
    private MailServer mailServer;
    @Mock
    private TemplateEngine templateEngine;
    @Mock
    private InputHelperImpl inputHelper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenTryToSendMessageWithClientOrTemplateIsNull() {
        Messenger messenger = new Messenger(mailServer, templateEngine, inputHelper);
        Client client = new Client("addresses", "draft message");
        Template template = new Template();

        when(templateEngine.generateMessage(null, client)).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, () -> messenger.sendMessage(null, template));
        assertThrows(NullPointerException.class, () -> messenger.sendMessage(client, null));
        assertThrows(NullPointerException.class, () -> messenger.sendMessage(null, null));
    }

    @Test
    public void shouldCreateTemplateAndClientWhenLaunch() {
        Messenger messenger = new Messenger(mailServer, templateEngine, inputHelper);

        when(inputHelper.getTemplate()).thenReturn("template text");
        when(inputHelper.getAddresses()).thenReturn("addresses");
        when(inputHelper.getDraftMessage()).thenReturn("draft message");

        when(templateEngine.generateMessage(any(Template.class), any(Client.class))).thenReturn("message");

        messenger.launch();

        verify(mailServer, only()).send(anyString(), anyString());
    }

    @Test
    public void shouldCallSendMethodOneTime() {
        Messenger messenger = new Messenger(mailServer, templateEngine, inputHelper);
        Client client = new Client("my_email@gmail.com", "myMessage");
        Template template = new Template();

        when(templateEngine.generateMessage(template, client)).thenReturn("generated message");

        messenger.sendMessage(client, template);

        verify(mailServer, only()).send(anyString(), anyString());
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
