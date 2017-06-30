package projectsalmon;

import java.util.ArrayList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Report {
	
	private String xtitle;
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<String> gpa = new ArrayList<String>();
	XYChart.Series series1 = new XYChart.Series();
	 public void enterData(ArrayList<String> bla)
	{
	        series1.setName("Gpa");       
	        series1.getData().add(new XYChart.Data("austria", 50));
	}
	 
	 public   XYChart.Series getData()
	 {
		 return series1;
	 }

	public String getXtitle() {
		return xtitle;
	}

	public void setXtitle(String xtitle) {
		this.xtitle = xtitle;
	}

}