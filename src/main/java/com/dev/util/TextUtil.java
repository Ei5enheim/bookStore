package com.dev.util;

public class TextUtil {

    public String sanitize (String txtToSanitize) {
        return txtToSanitize.replaceAll("\\s+", " ");
    }
}
