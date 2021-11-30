package com.epam.ld.module2.testing.server;

import com.epam.ld.module2.testing.MailServer;
import com.epam.ld.module2.testing.exception.AddressIsEmptyException;
import com.epam.ld.module2.testing.exception.MessageContentIsEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MailServerTest {

    @Test
    public void shouldThrowAddressIsEmptyExceptionWhenTryToSendMessageWithEmptyAddress() {
        MailServer mailServer = new MailServer();

        assertThrows(AddressIsEmptyException.class, () -> mailServer.send("", "content"));
    }

    @Test
    public void shouldThrowMessageContentIsEmptyExceptionWhenTryToSendMessageWithEmptyContent() {
        MailServer mailServer = new MailServer();

        assertThrows(MessageContentIsEmptyException.class, () -> mailServer.send("addresses", ""));
    }
}
