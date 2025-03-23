package org.CalculadoraIMC.utils;

import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return Paths.get("C:\\Users\\Iury\\Desktop\\ENG. SOFTWARE\\CalculadoraIMC\\src\\main\\java\\org\\CalculadoraIMC\\view").toAbsolutePath().toString();
    }
}
