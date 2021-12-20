package com.epam.ld.module2.testing.server;

import com.epam.ld.module2.testing.MailServer;
import com.epam.ld.module2.testing.exception.AddressIsEmptyException;
import com.epam.ld.module2.testing.exception.MessageContentIsEmptyException;
import com.epam.ld.module2.testing.utils.InputHelperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MailServerTest {

    @Mock
    private InputHelperImpl inputHelper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowAddressIsEmptyExceptionWhenTryToSendMessageWithEmptyAddress() {
        MailServer mailServer = new MailServer(inputHelper);

        assertThrows(AddressIsEmptyException.class, () -> mailServer.send("", "content"));
    }

    @Test
    public void shouldThrowMessageContentIsEmptyExceptionWhenTryToSendMessageWithEmptyContent() {
        MailServer mailServer = new MailServer(inputHelper);

        assertThrows(MessageContentIsEmptyException.class, () -> mailServer.send("addresses", ""));
    }

    @Test
    public void shouldCallWriteMethodOneTime() {
        MailServer mailServer = new MailServer(inputHelper);
        mailServer.send("my_address@gmail.com", "myMessage");
        verify(inputHelper, times(1)).write(anyString(),anyString());
    }
}