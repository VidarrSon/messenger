package com.epam.ld.module2.testing.messenger;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;
import com.epam.ld.module2.testing.Messenger;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MessengerTest {
    @Mock
    private MailServer mailServer;
    @Mock
    private TemplateEngine templateEngine;

    @Test
    public void shouldThrowNullPointerExceptionWhenTryToSendMessageWithClientOrTemplateIsNull() {
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = new Client();
        Template template = new Template();

        assertThrows(NullPointerException.class, () -> messenger.sendMessage(null, template));
        assertThrows(NullPointerException.class, () -> messenger.sendMessage(client, null));
        assertThrows(NullPointerException.class, () -> messenger.sendMessage(null, null));
    }

    @Test
    public void shouldCallSendMethodOneTime() {
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = new Client();
        Template template = new Template();

        when(templateEngine.generateMessage(template, client)).thenReturn("generated message");
        doNothing().when(mailServer).send(anyString(), anyString());
        messenger.sendMessage(client, template);
        verify(mailServer, times(1)).send(anyString(), anyString());
    }
}
