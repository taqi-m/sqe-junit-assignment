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
@Tag("slow") // Groups all tests in this class as 'slow'
class InterestCalculatorTest {

	private InterestCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new InterestCalculator();
	}

	// --- Tests for Simple Interest ---
    @Test
    @Order(1)
    @DisplayName("Test 1: Simple Interest - Valid Case")
    void testSimpleInterest_ValidCase() {
        // P=1000, R=5%, T=2 -> Expected: 100.0
        double interest = calculator.calculateSimpleInterest(1000, 5, 2);
        assertEquals(100.0, interest);
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Simple Interest - Zero Rate")
    void testSimpleInterest_ZeroRate() {
        double interest = calculator.calculateSimpleInterest(1000, 0, 2);
        assertEquals(0.0, interest);
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 3: Simple Interest - Zero Time")
    void testSimpleInterest_ZeroTime() {
        double interest = calculator.calculateSimpleInterest(1000, 5, 0);
        assertEquals(0.0, interest);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Simple Interest - Invalid Principal")
    void testSimpleInterest_InvalidPrincipal() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSimpleInterest(0, 5, 2);
        });
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Simple Interest - Invalid Rate")
    void testSimpleInterest_InvalidRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSimpleInterest(1000, -1, 2);
        });
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Simple Interest - Invalid Time")
    void testSimpleInterest_InvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSimpleInterest(1000, 5, -1);
        });
    }
}