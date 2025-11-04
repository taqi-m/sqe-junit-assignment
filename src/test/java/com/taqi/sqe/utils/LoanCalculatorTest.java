package com.taqi.sqe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    
 // --- Standard Valid ECP Test ---
    @Test
    @Order(1)
    @DisplayName("Test 1: Standard Valid Loan Calculation")
    void testStandardCalculation() {
        // P=100000, R=5%, Y=30 -> Expected: 536.82
        double payment = calculator.calculateMonthlyPayment(100000, 5, 30);
        assertEquals(536.82, payment, "Standard loan calculation should be correct");
    }

    // --- Valid BVA Test ---
    @Test
    @Order(2)
    @Tag("fast") // Override class tag; this is a simple edge case
    @DisplayName("Test 2: Valid Boundary - Zero Interest Rate")
    void testZeroInterestRateBoundary() {
        // P=12000, R=0%, Y=1 -> Expected: 1000.00
        double payment = calculator.calculateMonthlyPayment(12000, 0, 1);
        assertEquals(1000.00, payment, "A 0% interest loan should be principal / months");
    }
    
    // --- Valid BVA Test ---
    @Test
    @Order(3)
    @DisplayName("Test 3: Valid Boundary - One Year Loan")
    void testOneYearLoan() {
        double payment = calculator.calculateMonthlyPayment(50000, 7, 1);
        assertEquals(4326.34, payment, "One year loan calculation");
    }

    // --- Invalid BVA Tests (Testing Exceptions) ---
    @Test
    @Order(4)
    @DisplayName("Test 4: Invalid Boundary - Zero Principal")
    void testZeroPrincipal() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateMonthlyPayment(0, 5, 30);
        });
        assertEquals("Principal must be greater than zero.", e.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Invalid ECP - Negative Principal")
    void testNegativePrincipal() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateMonthlyPayment(-10000, 5, 30);
        });
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Invalid Boundary - Negative Rate")
    void testNegativeRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateMonthlyPayment(10000, -1, 10);
        });
    }


    @Test
    @Order(7)
    @DisplayName("Test 7: Invalid Boundary - Zero Years")
    void testZeroYears() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateMonthlyPayment(10000, 5, 0);
        });
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Invalid ECP - Negative Years")
    void testNegativeYears() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateMonthlyPayment(10000, 5, -5);
        });
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