module main.bubblesortvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.bubblesortvisualizer to javafx.fxml;
    exports main.bubblesortvisualizer;
}