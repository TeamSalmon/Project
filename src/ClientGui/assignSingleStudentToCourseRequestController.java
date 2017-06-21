/**
 * Sample Skeleton for 'assignSingleStudentToCourseRequestGUI.fxml' Controller Class
 */

package ClientGui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class assignSingleStudentToCourseRequestController {

    @FXML // fx:id="classCMB"
    private ComboBox<?> classCMB; // Value injected by FXMLLoader

    @FXML // fx:id="courseNumberTB"
    private TextField courseNumberTB; // Value injected by FXMLLoader

    @FXML // fx:id="cmbClass"
    private AnchorPane cmbClass; // Value injected by FXMLLoader

    @FXML // fx:id="backBt"
    private Button backBt; // Value injected by FXMLLoader

    @FXML // fx:id="studentNameTB"
    private TextArea studentNameTB; // Value injected by FXMLLoader

    @FXML // fx:id="sendRequestBt"
    private Button sendRequestBt; // Value injected by FXMLLoader

    @FXML // fx:id="studentIBTB"
    private TextField studentIBTB; // Value injected by FXMLLoader

    @FXML
    void sendRequest(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void classList(ActionEvent event) {

    }

}
