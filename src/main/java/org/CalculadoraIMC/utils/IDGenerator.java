package org.CalculadoraIMC.utils;

public class IDGenerator {
    private static long counter = 1;

    public static long generateID(){
        return counter++;
    }
}
