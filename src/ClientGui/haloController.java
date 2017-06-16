package ClientGui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;

public class haloController {

    @FXML // fx:id="bc"
    private BarChart<String,Number> bc; // Value injected by FXMLLoader

    @FXML // fx:id="yAxis"
    private NumberAxis yAxis; // Value injected by FXMLLoader

    @FXML // fx:id="xAxis"
    private CategoryAxis xAxis; // Value injected by FXMLLoader

    @FXML // fx:id="backPT"
    private Button backPT; // Value injected by FXMLLoader
    
    
    
    
}