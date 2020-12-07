package com.margoLobova.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void calculateTest() {
        Calculator calculator = new Calculator("* + 5 7 - 6 3");
        Double actual = calculator.calculate();
        Double expected = 36.0;
        assertEquals(expected,actual);
        calculator = new Calculator("* - + 6 3 4 2");
        actual = calculator.calculate();
        expected = 10.0;
        assertEquals(expected,actual);
        calculator = new Calculator("sin + - 1 2 1");
        actual = calculator.calculate();
        expected = 0.0;
        assertEquals(expected,actual);
    }
}