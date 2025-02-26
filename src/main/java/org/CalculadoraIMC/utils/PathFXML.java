package org.CalculadoraIMC.utils;

import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return Paths.get("C:\\Users\\Unifan\\Documents\\AQUIII\\CalculadoraIMC\\src\\main\\java\\org\\CalculadoraIMC\\view").toAbsolutePath().toString();
    }
}
