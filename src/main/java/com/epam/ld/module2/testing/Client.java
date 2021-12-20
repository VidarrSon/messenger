package com.epam.ld.module2.testing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Client.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Client {
    private String addresses;
    private String draftMessage;
}
