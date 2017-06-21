package ClientGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class CoursesTabController implements Initializable
{
	@FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML
    private ListView<String> coursesMenu;
    
    private ObservableList<String> data;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		data = FXCollections.observableArrayList("new");
        coursesMenu.setItems(data);
	}
    
}
