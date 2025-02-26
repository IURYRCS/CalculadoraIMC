module com.example.CalculadoraIMC {
    requires javafx.controls;
    requires javafx.fxml;



    opens org.CalculadoraIMC to javafx.fxml;
    exports org.CalculadoraIMC;
    opens org.CalculadoraIMC.controller to javafx.fxml;

}