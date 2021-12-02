package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.Client;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

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

    public Client generateClientBasedOnData(String data) {
        List<String> dataList = Arrays.asList(data.split(","));
        if (dataList.size() != 5) {
            return new Client();
        }
        return Client.builder()
                .subject(dataList.get(0))
                .addresses(dataList.get(1))
                .greeting(dataList.get(2))
                .body(dataList.get(3))
                .signature(dataList.get(4))
                .build();
    }
}
