package application;

//import gui.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConnectScreenController {

	
	
    @FXML
    private TextField TF_IP;

    @FXML
    private TextField TF_Port;

    @FXML
    private Button BTN_Connect;

    @FXML
    private Label LBL_Error;

    @FXML
	void buttonConnectPressed(ActionEvent event) {
		main.showGUI();
	}
    private Main main;
    
    public void setMainApp(Main main) {
		this.main = main;
	}
    
    public void init() {
    	
    }
    
}