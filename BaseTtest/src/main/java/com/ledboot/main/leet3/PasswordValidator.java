package com.ledboot.main.leet3;

/**
 * @author: Fred
 * @date: 2024/2/21 18:41
 * @description: (类的描述)
 */
public class PasswordValidator {

    public static String solution(String password) {

        if (password == null || password.length() < 8 || password.length() > 22) {
            return "weak";

        }
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        int consecutiveCount = 1;

        char lastChar = password.charAt(0);

        for (int i = 1; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = true;
            }
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }

            if (ch == lastChar) {
                consecutiveCount++;
                if (consecutiveCount >= 3) {
                    return "weak";
                }
            } else {
                consecutiveCount = 1;
                lastChar = ch;
            }

        }

        if (hasLower && hasUpper && hasDigit) {
            return "strong";
        } else {
            return "weak";
        }

    }


}
