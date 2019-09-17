package app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;


public class StringHelper {

    public static String splitBeforeAndAfter(String input, String before, String after) {
        String curr;
        curr = removeBefore(input, before);
        curr = removeAfter(curr, after);
        return curr;
    }

    public static String removeAfter(String input, String token) {
        return substringBefore(input, token);
    }


    private static String removeBefore(String input, String token) {
        return substringAfter(input, token);
    }

    public static List<String> splitToListByNewLine(String str) {
        return Stream.of(str.split("\n"))
                .map(String::new)
                .collect(Collectors.toList());
    }
}
