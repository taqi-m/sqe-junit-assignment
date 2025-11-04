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

	//Tests for compound interest
	@Test
	@Order(1)
	@DisplayName("Test 7: Compound Interest - Valid Case (Annual)")
	void testCompoundInterest_ValidCaseAnnual() {
		// P=1000, R=5%, N=1, T=2 -> Expected: 102.50
		double interest = calculator.calculateCompoundInterest(1000, 5, 1, 2);
		assertEquals(102.50, interest);
	}

	@Test
	@Order(2)
	@DisplayName("Test 8: Compound Interest - Valid Case (Monthly)")
	void testCompoundInterest_ValidCaseMonthly() {
		// P=1500, R=4.3%, N=12, T=6 -> Expected: 438.29
		double interest = calculator.calculateCompoundInterest(1500, 4.3, 12, 6);
		assertEquals(440.61, interest);
	}

	@Test
	@Order(3)
	@DisplayName("Test 9: Compound Interest - Invalid Principal")
	void testCompoundInterest_InvalidPrincipal() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.calculateCompoundInterest(0, 5, 1, 2);
		});
	}

	@Test
	@Order(4)
	@DisplayName("Test 10: Compound Interest - Invalid Compounds")
	void testCompoundInterest_InvalidCompounds() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.calculateCompoundInterest(1000, 5, 0, 2);
		});
	}


	//3 test cases from the CSV file for Simple Interest
	@ParameterizedTest(name = "Test {index}: P={0}, R={1}, T={2} -> SI={3}")
	@CsvFileSource(resources = "/interestData.csv", numLinesToSkip = 1)
	@Tag("integration")
	@Order(5)
	@DisplayName("Test 11-13: Simple Interest from CsvSource")
	void testSimpleInterest_DataDriven(double p, double r, double t, double expected) {
		assertEquals(expected, calculator.calculateSimpleInterest(p, r, t));
	}
}