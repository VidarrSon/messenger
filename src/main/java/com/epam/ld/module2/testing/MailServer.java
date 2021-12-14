package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exception.AddressIsEmptyException;
import com.epam.ld.module2.testing.exception.MessageContentIsEmptyException;
import com.epam.ld.module2.testing.utils.ConsoleHelper;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        if (addresses.isEmpty()) {
            throw new AddressIsEmptyException("Address is empty!");
        }
        if (messageContent.isEmpty()) {
            throw new MessageContentIsEmptyException("Content is empty!");
        }
        ConsoleHelper.writeMessage("The email was sent successfully!");
        ConsoleHelper.writeMessage("--------------------------------");
        ConsoleHelper.writeMessage("To: " + addresses);
        ConsoleHelper.writeMessage(messageContent);
        ConsoleHelper.writeMessage("--------------------------------");
    }
}
