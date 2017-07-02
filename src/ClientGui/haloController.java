package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import controllers.reportController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class haloController implements Initializable {
	private static final NumberFormat FORMAT = NumberFormat.getNumberInstance();
	 public static final double NULL_VALUE = Double.MAX_VALUE;
	Main	myMain=	Main.getInstance();
    @FXML // fx:id="bc"
    private BarChart<String,Number> bc; // Value injected by FXMLLoader

    @FXML // fx:id="yAxis"
    private NumberAxis yAxis; // Value injected by FXMLLoader

    @FXML // fx:id="xAxis"
    private CategoryAxis xAxis; // Value injected by FXMLLoader

    @FXML // fx:id="backPT"
    private Button backPT; // Value injected by FXMLLoader

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
		XYChart.Series series1 = new XYChart.Series();
		 series1.setName("Gpa"); 
		 
		 for(int i=0;i<Main.repCon.name.size();i++)
		 {
			String s=Main.repCon.grade.get(i).toString();
			
			float f = Float.parseFloat(s);
		     
	        series1.getData().add(new XYChart.Data(Main.repCon.name.get(i),f));
		 }
		bc.getData().addAll(series1);
	}
	
	
	
    @FXML
    void GoBack(ActionEvent event) throws IOException 
    {

    	((Node)event.getSource()).getScene().getWindow().hide();
    	
    	 myMain.getMange().changeScenereport((Scene) myMain.getMange().myStack.pop());
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	}
