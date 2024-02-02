package com.example.temperatureconversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TempConversionControllerTest {
    private final TempConversionController conversionController = new TempConversionController();
    private final double delta = 0.000001;

    @Test
    void testIsNumeric() {
        assertTrue(conversionController.isNumeric("123"));
        assertTrue(conversionController.isNumeric("123.0320"));
        assertFalse(conversionController.isNumeric("-"));
        assertFalse(conversionController.isNumeric(""));
        assertFalse(conversionController.isNumeric("123.a"));
        assertFalse(conversionController.isNumeric("abd"));
    }

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(conversionController.CelsiusToFahrenheit(0), 32, delta);
        assertEquals(conversionController.CelsiusToFahrenheit(-123), -189.4, delta);
        assertEquals(conversionController.CelsiusToFahrenheit(456.4356), 853.58408, delta);
    }

    @Test
    void testFahrenheitToKelvin() {
        assertEquals(conversionController.FahrenheitToKelvin(0), 255.37222222, delta);
        assertEquals(conversionController.FahrenheitToKelvin(-123), 187.03888889, delta);
        assertEquals(conversionController.FahrenheitToKelvin(456.4356), 508.94755556, delta);
    }

    @Test
    void testKelvinToCelsius() {
        assertEquals(conversionController.KelvinToCelsius(0), -273.15, delta);
        assertEquals(conversionController.KelvinToCelsius(-123), -396.15, delta);
        assertEquals(conversionController.KelvinToCelsius(456.4356), 183.2856, delta);
    }
}