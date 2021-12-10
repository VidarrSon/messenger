package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.TemplateEngine;

import java.util.Arrays;

public class ApplicationRun {
    public static void main(String[] args) {
        Messenger messenger = new Messenger(new MailServer(), new TemplateEngine());
        if (args.length == 0) {
            messenger.runConsoleMode();
        } else {
            messenger.runFileMode(Arrays.asList(args));
        }
    }
}
