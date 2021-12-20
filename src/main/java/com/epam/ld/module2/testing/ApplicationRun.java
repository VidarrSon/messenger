package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.ConsoleInputHelper;
import com.epam.ld.module2.testing.utils.FileInputHelper;
import com.epam.ld.module2.testing.utils.InputHelperImpl;

import java.util.Arrays;

public class ApplicationRun {
    /**
     * Run the application.
     *
     * @param args   the arguments of input/output data in the file mode
     */
    public static void main(String[] args) {
        InputHelperImpl inputHelper = args.length == 0 ?
                new ConsoleInputHelper() : new FileInputHelper(Arrays.asList(args));
        Messenger messenger = new Messenger(new MailServer(inputHelper), new TemplateEngine(), inputHelper);
        messenger.launch();
    }
}
