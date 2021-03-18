package org.test.assignment;


public class StringValidator {

    private final String userInput;
    private final ValidationResponse response;

    public StringValidator(String userInput) {
        this.userInput = userInput;
        this.response = new ValidationResponse();

    }

    public ValidationResponse validate() {
        if (userInput.isEmpty()) {
            response.setInvalidResponse("You entered empty line.");
            return response;
        }

        int lastIndex = userInput.length() - 1;
        int bracketCounter = 0;
        boolean isTextPart = false;
        StringBuilder stringNumber = new StringBuilder();
        for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);

            // Drop text when number starts
            if (Character.isDigit(c)) {
                stringNumber.append(c);
                isTextPart = false;

                // Check the number preceding to opening bracket
            } else if (c == '[') {
                try {
                    if (isTextPart || stringNumber.length() == 0) {
                        response.setInvalidResponse("You have not entered the number before brackets: \n" +
                                userInput + "\n" + " ".repeat(i) + "^");
                        return response;
                    } else if (Integer.parseInt(stringNumber.toString()) == 0) {
                        throw new NumberFormatException();
                    } else if (stringNumber.charAt(0) == '0') {
                        response.setInvalidResponse("You entered wrong number: \"" + stringNumber +
                                "\". Number shouldn't start with 0.\n" + userInput + "\n" +
                                " ".repeat(i - stringNumber.length()) + "^".repeat(stringNumber.length()));
                        return response;
                    }
                    stringNumber.setLength(0);
                } catch (NumberFormatException e) {
                    response.setInvalidResponse("You entered wrong number: \"" + stringNumber +
                            "\".\nIt must be in the range from 1 to 2147483639.\n" + userInput + "\n" +
                            " ".repeat(i - stringNumber.length()) + "^".repeat(stringNumber.length()));
                    return response;
                }
                bracketCounter++;

                // Check if number not mixed into text
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                if (stringNumber.length() != 0) {
                    response.setInvalidResponse("You entered text directly after number:\n"
                            + userInput + "\n" + " ".repeat(i) + "^");
                    return response;
                }
                isTextPart = true;

                // Check number of brackets
            } else if (c == ']') {
                bracketCounter--;
                if (bracketCounter < 0) {
                    response.setInvalidResponse("You entered closing bracket without corresponding opening bracket:\n" +
                            userInput + "\n" + " ".repeat(i) + "^");
                    return response;
                } else if (!isTextPart) {
                    response.setInvalidResponse("You entered closing bracket without text before it:\n" +
                            userInput + "\n" + " ".repeat(i) + "^");
                    return response;
                }
                if (i == lastIndex || userInput.charAt(i + 1) != ']') {
                    isTextPart = false;
                }

                // Any other character not allowed
            } else {
                response.setInvalidResponse("You entered forbidden character: \"" + c + "\".\n" +
                        "Only latin letters, digits and brackets are allowed.\n" + userInput + "\n" +
                        " ".repeat(i) + "^");
                return response;
            }
        }

        if (stringNumber.length() != 0) {
            response.setInvalidResponse("You entered number without further opening bracket:\n" +
                    userInput + "\n" + " ".repeat(lastIndex) + "^");
            return response;
        } else if (bracketCounter > 0) {
            response.setInvalidResponse("You entered opening bracket without closing it:\n" +
                    userInput + "\n" + " ".repeat(lastIndex) + "^");
            return response;
        }

        response.setStringValid(true);
        return response;
    }

    public ValidationResponse getResponse() {
        if (response == null) {
            validate();
        }
        return response;
    }

    static class ValidationResponse {

        private boolean stringValid = false;
        private String invalidResponse = "";

        public boolean isStringValid() {
            return stringValid;
        }

        public void setStringValid(boolean stringValid) {
            this.stringValid = stringValid;
        }

        public String getInvalidResponse() {
            return invalidResponse;
        }

        public void setInvalidResponse(String invalidResponse) {
            this.invalidResponse = invalidResponse;
        }
    }
}
