package com.epam.mjc;

import java.sql.Array;
import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        for (String delimiter: delimiters) {
            source = source.replaceAll(delimiter, "?");
        }
        String[] array = source.split("\\?");
        for (String string: array) {
            if (!string.equals("")) {
                result.add(string);
            }
        }
        return result;
    }
}
