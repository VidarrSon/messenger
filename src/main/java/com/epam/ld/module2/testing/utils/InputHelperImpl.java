package com.epam.ld.module2.testing.utils;

import static com.epam.ld.module2.testing.utils.PlaceholderConstants.ADDRESSES_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.ADDRESSES_START_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.DRAFT_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.DRAFT_START_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.TEMPLATE_END_PLACEHOLDER;
import static com.epam.ld.module2.testing.utils.PlaceholderConstants.TEMPLATE_START_PLACEHOLDER;

public abstract class InputHelperImpl implements InputHelper {
    String content;

    public abstract void read();
    public abstract void write(String addresses, String messageContent);

    @Override
    public String getTemplate() {
        return getValue(TEMPLATE_START_PLACEHOLDER, TEMPLATE_END_PLACEHOLDER);
    }

    @Override
    public String getAddresses() {
        return getValue(ADDRESSES_START_PLACEHOLDER, ADDRESSES_END_PLACEHOLDER);
    }

    @Override
    public String getDraftMessage() {
        return getValue(DRAFT_START_PLACEHOLDER, DRAFT_END_PLACEHOLDER);
    }

    private String getValue(String valueStartPlaceholder, String valueEndPlaceholder) {
        String value = "";
        String content = this.content;

        if (content.contains(valueStartPlaceholder) && content.contains(valueEndPlaceholder)) {
            value = content.substring(content.indexOf(valueStartPlaceholder) + valueStartPlaceholder.length(),
                    content.indexOf(valueEndPlaceholder));
        }

        return value;
    }

}
