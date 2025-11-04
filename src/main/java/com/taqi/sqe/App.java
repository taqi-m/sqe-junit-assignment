package com.taqi.sqe;

import com.taqi.sqe.utils.InterestCalculator;
import com.taqi.sqe.utils.LoanCalculator;
import com.taqi.sqe.utils.InputValidator;

public class App {
    public static void main(String[] args) {
        InterestCalculator interestCalc = new InterestCalculator();
        LoanCalculator loanCalc = new LoanCalculator();
        InputValidator validator = new InputValidator();

        // Simple Interest
        double simpleInterest = interestCalc.calculateSimpleInterest(1000, 5, 2);
        System.out.println("Simple Interest (P=1000, R=5%, T=2): " + simpleInterest);

        // Compound Interest
        double compoundInterest = interestCalc.calculateCompoundInterest(1000, 5, 1, 2);
        System.out.println("Compound Interest (P=1000, R=5%, N=1, T=2): " + compoundInterest);

        // Loan Payment
        double monthlyPayment = loanCalc.calculateMonthlyPayment(10000, 7, 5);
        System.out.println("Monthly Loan Payment (P=10000, R=7%, Y=5): " + monthlyPayment);

        // Email Validation
        String email = "user@example.com";
        boolean isEmailValid = validator.isValidEmail(email);
        System.out.println("Is '" + email + "' a valid email? " + isEmailValid);
    }
}