/**
 * Sample Skeleton for 'TeacherGUI.fxml' Controller Class
 */

package ClientGui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Teacher {

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="firstTab"
    private AnchorPane firstTab; // Value injected by FXMLLoader

    @FXML // fx:id="userControl"
    private ComboBox<?> userControl; // Value injected by FXMLLoader

    @FXML // fx:id="userImage"
    private ImageView userImage; // Value injected by FXMLLoader

    @FXML
    void userControl(ActionEvent event) {

    }

}