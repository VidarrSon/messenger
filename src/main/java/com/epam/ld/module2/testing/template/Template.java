package com.epam.ld.module2.testing.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Template.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    private String templateText = "Subject: #{subject}\n\n#{greeting}\n\n#{body}\n\n#{signature}";
}
