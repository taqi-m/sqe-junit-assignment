package com.taqi.sqe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("slow")
class LoanCalculatorTest {

    private LoanCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new LoanCalculator();
    }
    
    @Test
    @Order(1)
    @DisplayName("Test 9: High Rate and Long Term")
    void testHighRateLoan() {
        double payment = calculator.calculateMonthlyPayment(500000, 12.5, 30);
        assertEquals(5336.29, payment, "High rate, long term loan");
    }

    
    //5 test cases from the CSV file.
    @ParameterizedTest(name = "Test {index}: P={0}, R={1}%, Y={2} -> Expected {3}")
    @CsvFileSource(resources = "/loanData.csv", numLinesToSkip = 1)
    @Tag("integration")
    @Order(2)
    @DisplayName("Test 10-14: Calculate Correctly from CSV File")
    void testLoanCalculationsFromCSV(double principal, double rate, int years, double expectedPayment) {
        double actualPayment = calculator.calculateMonthlyPayment(principal, rate, years);
        assertEquals(expectedPayment, actualPayment);
    }
}