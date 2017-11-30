package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ui.ClientUI;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	BorderPane root = new BorderPane();
	Stage primaryStage = new Stage();
	public GuiController guicontroller = new GuiController();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Fische Verschenken");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("rootLayout.fxml"));
			root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			
			
			
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,800,600);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			primaryStage.show();
			showLogin();
			ClientUI client = new ClientUI();
			client.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showLogin() {
		try {
			// Load Loginscreen fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ConnectionScreen.fxml"));
			AnchorPane GUI = (AnchorPane) loader.load();
			ConnectScreenController controller = loader.getController();
			controller.setMainApp(this);
			root.setCenter(GUI);
			controller.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGUI() {
		try {
			// Load Loginscreen fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("GUI.fxml"));
			AnchorPane GUI = (AnchorPane) loader.load();
			this.guicontroller = loader.getController();
			this.guicontroller.setMainApp(this);
			root.setCenter(GUI);
			this.guicontroller.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showRetard() {
		try {
			// Load Loginscreen fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Retarded.fxml"));
			AnchorPane GUI = (AnchorPane) loader.load();
			ConnectScreenController controller = loader.getController();
			controller.setMainApp(this);
			root.setCenter(GUI);
			controller.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//ui.ClientUI.mainClientUI();
		launch(args);
	}
	
	public Main getMainApp() {
		return this;
	}
	
}
