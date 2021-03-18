package org.test.assignment;

import org.test.assignment.StringValidator.ValidationResponse;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        String userInput;
        StringValidator validator; // move out of cycle
        while (true) {
            System.out.print("Enter string for unpacking or \"exit\" to finish the program: ");
            userInput = userInputScanner.nextLine();
            validator = new StringValidator(userInput);
            ValidationResponse response = validator.validate();
            if ("exit".equals(userInput)) {
                System.exit(0);
            } else if (response.isStringValid()) {
                System.out.println(unpackString(userInput));
            } else {
                System.out.println(response.getInvalidResponse());
                System.out.println("String should be in format \"10[xyz]\", where:\n" +
                        "10 - number of times to repeat substring\n" +
                        "xyz - string to repeat");
            }
            System.out.println("\n");
        }
    }

    static String unpackString(String userInput) {
        final Function<MatchResult, String> replacer = matchResult -> {
            int quantifier = Integer.parseInt(matchResult.group(1));
            String repeatingString = matchResult.group(2);
            return repeatingString.repeat(quantifier);
        };
        Pattern pattern = Pattern.compile("(\\d+)\\[([a-zA-Z]+)]");
        Matcher matcher = pattern.matcher(userInput);
        String unpackedString = userInput;
        while (matcher.find()) {
            unpackedString = matcher.replaceAll(replacer);
            matcher.reset(unpackedString);
        }
        return unpackedString;
    }
}
