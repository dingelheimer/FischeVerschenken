package application;

import java.util.ArrayList;

import gamelogic.SchiffMap;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;

public class GuiController {

    @FXML
    private GridPane GRD_Spieler;

    @FXML
    private GridPane GRD_Gegner;
    
    private ArrayList<ArrayList<Label>> list = new ArrayList<ArrayList<Label>>();
    
    
    
    private Main main;
    
    public void setMainApp(Main main) {
		this.main = main;
	}
    
    
    
    public void init() {
    	for(int i = 0; i < 12; i++) {
    		this.list.add(i, new ArrayList<Label>());
    		
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 0; j < 12; j++) {
    			this.list.get(i).add(j, new Label());
    		}
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 1; j < 12; j++) {
    			this.GRD_Spieler.add(this.list.get(i).get(j), i, j, 1, 1);
    		}
    	}
    	
    	//this.list.get(3).get(5).setText("  V");
    	
    	
    	
    }
    
    
    public void fuelleSpielerMap(SchiffMap map) {
    	String str = "";
    	for(int i = 1; i < 12; i++) {
    		for(int j = 1; j < 12; j++) {
    			//if(map.)
    			
    			this.list.get(i).get(j).setText(str);
    		}
    	}
    }
    

}
