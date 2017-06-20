/**
 * Sample Skeleton for 'TeacherGui.fxml' Controller Class
 */

package ClientGui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;

public class Teacher {

    @FXML // fx:id="container"
    private TabPane container; // Value injected by FXMLLoader

    @FXML // fx:id="userControl"
    private ComboBox<?> userControl; // Value injected by FXMLLoader

    @FXML
    void userControl(ActionEvent event) {

    }

}
