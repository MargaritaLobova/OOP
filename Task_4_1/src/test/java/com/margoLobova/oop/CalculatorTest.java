package com.margoLobova.oop;

import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // -- GENERAL TESTS -- //

    @Test
    void easyTest() {
        Calculator calculator = new Calculator("* + 5 7 - 6 3");
        Double actual = calculator.calculate();
        Double expected = 36.0;
        assertEquals(expected, actual);
        calculator = new Calculator("* - + 6 3 4 2");
        actual = calculator.calculate();
        expected = 10.0;
        assertEquals(expected, actual);
        calculator = new Calculator("sin + - 1 2 1");
        actual = calculator.calculate();
        expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void BigTest() {
        Calculator calculator = new Calculator("+ 9 + * 10 / 11 - 12 13 + 14 + * 15 / 16 * 17 * 18 * 19 * 20 21 - 22 23");
        Double actual = calculator.calculate();
        assertEquals(-87.999901715072, actual);
    }

    // -- EXCEPTIONS TESTS -- //

    @Test
    void emptyTest() {
        Calculator calculator = new Calculator("");
        try {
            calculator.calculate();
        } catch (IllegalArgumentException e) {
            assertEquals("Empty string!", e.getMessage());
        }
    }

    @Test
    void strangeInputTest() {
        Calculator calculator = new Calculator("rfssdcsdc ddscscsd sdcsscscsd dsd 434 343");
        assertThrows(IllegalArgumentException.class, calculator::calculate);
    }

    @Test
    void strangeBigTest() {
        Calculator calculator = new Calculator("5 + 6 * 7 / ( 8 - 9 ) + 10 + 11 / 12 * 13 * 14 * 15 * 16 * 17 + 18 - 19");
        assertThrows(EmptyStackException.class, calculator::calculate);
    }
}