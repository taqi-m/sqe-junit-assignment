package com.taqi.sqe.utils;

public class InterestCalculator {

    /**
     * Calculates simple interest.
     * Formula: I = P * R * T
     *
     * @param principal The initial amount. Must be > 0.
     * @param rate The annual interest rate as a percentage (e.g., 5.0). Must be >= 0.
     * @param timeInYears The time the money is invested for, in years. Must be >= 0.
     * @return The total simple interest earned.
     */
    public double calculateSimpleInterest(double principal, double rate, double timeInYears) {
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Rate must be greater than or equal to zero.");
        }
        if (timeInYears < 0) {
            throw new IllegalArgumentException("Time in years must be greater than or equal to zero.");
        }
        
        double rateDecimal = rate / 100.0;
        double interest = principal * rateDecimal * timeInYears;
        
        return Math.round(interest * 100.0) / 100.0;
    }

    /**
     * Calculates compound interest.
     * Formula: A = P(1 + r/n)^(nt)
     *
     * @param principal The initial amount. Must be > 0.
     * @param rate The annual interest rate as a percentage (e.g., 5.0). Must be >= 0.
     * @param compoundsPerYear The number of times interest is compounded per year. Must be > 0.
     * @param timeInYears The time the money is invested for, in years. Must be > 0.
     * @return The total compound interest earned (Total Amount - Principal).
     */
    public double calculateCompoundInterest(double principal, double rate, int compoundsPerYear, double timeInYears) {
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Rate must be greater than or equal to zero.");
        }
        if (compoundsPerYear <= 0) {
            throw new IllegalArgumentException("Compounds per year must be greater than zero.");
        }
        if (timeInYears <= 0) {
            throw new IllegalArgumentException("Time in years must be greater than zero.");
        }

        double rateDecimal = rate / 100.0;
        double n = compoundsPerYear;
        double t = timeInYears;
        
        double amount = principal * Math.pow(1 + (rateDecimal / n), n * t);
        double interest = amount - principal;

        return Math.round(interest * 100.0) / 100.0;
    }
}