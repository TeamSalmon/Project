package ClientGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class haloController implements Initializable {

	Main	myMain=	Main.getInstance();
    @FXML // fx:id="bc"
    private BarChart<String,Number> bc; // Value injected by FXMLLoader

    @FXML // fx:id="yAxis"
    private NumberAxis yAxis; // Value injected by FXMLLoader

    @FXML // fx:id="xAxis"
    private CategoryAxis xAxis; // Value injected by FXMLLoader

    @FXML // fx:id="backPT"
    private Button backPT; // Value injected by FXMLLoader

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
		
		XYChart.Series series1 = new XYChart.Series();
        series1.setName("Gpa");       
        series1.getData().add(new XYChart.Data("austria", 50));
        series1.getData().add(new XYChart.Data("brazil", 60));
        series1.getData().add(new XYChart.Data("france", 70));
        series1.getData().add(new XYChart.Data("italy", 80));
        series1.getData().add(new XYChart.Data("usa", 90));   
        bc.getData().addAll(series1);
		
		
	}
	
    
}