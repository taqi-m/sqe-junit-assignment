package com.taqi.sqe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    // Regex pattern for a basic email validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Checks if a given amount is a positive value (greater than zero).
     *
     * @param amount The amount to check.
     * @return true if amount > 0, false otherwise.
     */
    public boolean isPositiveAmount(double amount) {
        return amount > 0;
    }

    /**
     * Validates an email address against a basic regex pattern.
     *
     * @param email The email String to validate.
     * @return true if the email format is valid, false otherwise.
     */
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}