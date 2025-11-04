package com.taqi.sqe.utils;

public class LoanCalculator {

    /**
     * Calculates the fixed monthly payment for a loan.
     *
     * @param principal The total loan amount (e.g., 300000). Must be > 0.
     * @param annualRate The annual interest rate as a percentage (e.g., 5.0). Must be >= 0.
     * @param years The loan term in years. Must be > 0.
     * @return The calculated monthly payment.
     */
    public double calculateMonthlyPayment(double principal, double annualRate, int years) {
        
        // --- Input Validation ---
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }
        if (annualRate < 0) {
            throw new IllegalArgumentException("Annual rate must be greater than or equal to zero.");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("Years must be greater than zero.");
        }

        // --- Calculation Logic ---
        double monthlyPayment;
        int numberOfPayments = years * 12;

        // Special case: 0% interest (avoids division by zero)
        if (annualRate == 0) {
            monthlyPayment = principal / numberOfPayments;
        } else {
            // Convert annual rate from percentage to a monthly decimal
            double monthlyRate = annualRate / 100.0 / 12.0;

            // Using the standard loan payment formula
            monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) 
                                    / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
        }

        // Round to 2 decimal places for currency
        return Math.round(monthlyPayment * 100.0) / 100.0;
    }
}
