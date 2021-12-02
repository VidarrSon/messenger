package com.epam.ld.module2.testing.messenger;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;
import com.epam.ld.module2.testing.Messenger;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class MessengerTest {
    @Mock
    private MailServer mailServer;
    @Mock
    private TemplateEngine templateEngine;

    /*@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }*/

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
        MockitoAnnotations.initMocks(this);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = new Client();
        client.setAddresses("my_email@gmail.com");
        Template template = new Template();

        when(templateEngine.generateMessage(template, client)).thenReturn("generated message");
        doNothing().when(mailServer).send(anyString(), anyString());
        messenger.sendMessage(client, template);
        verify(mailServer, times(1)).send(anyString(), anyString());
    }
}
