package com.taqi.sqe.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("fast")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InputValidatorTest {

    private InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    //Tests for isPositiveAmount
    @Test
    @Order(1)
    @DisplayName("Test 1: Positive Amount - Valid")
    void testIsPositive_WithPositiveNumber() {
        assertTrue(validator.isPositiveAmount(100.0));
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 2: Positive Amount - Valid (Small Decimal)")
    void testIsPositive_WithSmallDecimal() {
        assertTrue(validator.isPositiveAmount(0.01));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Positive Amount - Invalid Boundary (Zero)")
    void testIsPositive_WithZero() {
        assertFalse(validator.isPositiveAmount(0.0));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Positive Amount - Invalid Boundary (Negative)")
    void testIsPositive_WithNegativeNumber() {
        assertFalse(validator.isPositiveAmount(-10.0));
    }

    //Tests for isValidEmail
    @Test
    @Order(5)
    @DisplayName("Test 5: Email - Invalid (Null)")
    void testEmail_WithNullInput() {
        assertFalse(validator.isValidEmail(null));
    }

    //4 test cases
    @Order(6)
    @ParameterizedTest(name = "Test {index}: Valid Email = {0}")
    @ValueSource(strings = {"test@example.com","user.name@example.co.uk","user-name@example.org","123@sub.domain.io"})
    @DisplayName("Test 6-9: Valid Email Formats")
    void testEmail_ValidCases(String email) {
        assertTrue(validator.isValidEmail(email));
    }
}
