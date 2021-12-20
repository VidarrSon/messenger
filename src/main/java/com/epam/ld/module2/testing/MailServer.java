package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exception.AddressIsEmptyException;
import com.epam.ld.module2.testing.exception.MessageContentIsEmptyException;
import com.epam.ld.module2.testing.utils.InputHelperImpl;

/**
 * Mail server class.
 */
public class MailServer {

    private final InputHelperImpl inputHelper;

    public MailServer(InputHelperImpl inputHelper) {
        this.inputHelper = inputHelper;
    }

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
        inputHelper.write(addresses, messageContent);
    }
}
