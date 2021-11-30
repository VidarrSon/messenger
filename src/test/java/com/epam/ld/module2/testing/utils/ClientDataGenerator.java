package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientDataGenerator {
    public Client generateClient() {
        return Client.builder()
                .subject("Good news!")
                .addresses("address@gmail.com")
                .greeting("Hello there!")
                .body("Long long long text")
                .signature("Best regards, unknown person.")
                .build();
    }
}
