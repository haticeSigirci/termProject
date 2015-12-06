package com.example.haticesigirci.termproject.Helpers;

/**
 * Created by haticesigirci on 06/12/15.
 */
public class TemperatureFormatter {

    public static String format(float temperature) {
        return String.valueOf(Math.round(temperature)) + "°";
    }
}

