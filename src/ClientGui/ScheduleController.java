package ClientGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import projectsalmon.Class;

public class ScheduleController implements Initializable
{
	@FXML
	private TableView<Class> table;
	@FXML
	private TableColumn<Class,String> sunday;
	@FXML
	private TableColumn<Class,String> tuesday;
	@FXML
	private TableColumn<Class,String> friday;
	@FXML
	private TableColumn<Class,String> thursday;
	@FXML
	private TableColumn<Class,String> wednesday;
	@FXML
	private TableColumn<Class,String> time;
	@FXML
	private TableColumn<Class,String> monday;
	private ObservableList<String> classList;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//time.set
	}
	
}
