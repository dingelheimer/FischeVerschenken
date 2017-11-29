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

	public boolean showSchiffeInSpielerMap = true;
	public boolean showSchiffeInGegnerMap = true;
	
	
    @FXML
    private GridPane GRD_Spieler;

    @FXML
    private GridPane GRD_Gegner;
    
    private ArrayList<ArrayList<Label>> listSpieler = new ArrayList<ArrayList<Label>>();
    private ArrayList<ArrayList<Label>> listGegner = new ArrayList<ArrayList<Label>>();
    
    
    private Main main;
    
    public void setMainApp(Main main) {
		this.main = main;
	}
    
    
    
    public void init() {
    	for(int i = 0; i < 12; i++) {
    		this.listSpieler.add(i, new ArrayList<Label>());
    		
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 0; j < 12; j++) {
    			this.listSpieler.get(i).add(j, new Label());
    		}
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 1; j < 12; j++) {
    			this.GRD_Spieler.add(this.listSpieler.get(i).get(j), i, j, 1, 1);
    		}
    	}
    	
    	
    	
    	for(int i = 0; i < 12; i++) {
    		this.listGegner.add(i, new ArrayList<Label>());
    		
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 0; j < 12; j++) {
    			this.listGegner.get(i).add(j, new Label());
    		}
    	}
    	
    	for(int i = 1; i < 12; i++) {
    		for(int j = 1; j < 12; j++) {
    			this.GRD_Spieler.add(this.listGegner.get(i).get(j), i, j, 1, 1);
    		}
    	}
    	
    	//this.list.get(3).get(5).setText("  V");
    	
    	SchiffMap map = new SchiffMap();
    	map.setzeSchiffNeu(2, 6, true, 3);
    	fuelleSpielerMap(map);
    }
    
    
    public void fuelleSpielerMap(SchiffMap map) {
    	String str = "";
    	int[][] feld = map.getFeld();
    	for(int i = 1; i < 11; i++) {
    		for(int j = 1; j < 11; j++) {
    			str = "";
    			if(feld[j][i] == 1 && showSchiffeInSpielerMap) {
    				str = "  S";
    			}
				if(feld[j][i] == 5 && showSchiffeInSpielerMap) {
					str = "  E";
				}
    			if(feld[j][i] == -1) {
    				str = "  X";
    			}
    			if(feld[j][i] == -2) {
    				str = "  o";
    			}
    			
    			this.listSpieler.get(j).get(i).setText(str);
    		}
    	}
    }
    
    public void fuelleGegnerMap(SchiffMap map) {
    	String str = "";
    	int[][] feld = map.getFeld();
    	for(int i = 1; i < 11; i++) {
    		for(int j = 1; j < 11; j++) {
    			str = "";
    			if(feld[j][i] == 1 && showSchiffeInSpielerMap) {
    				str = "  S";
    			}
				if(feld[j][i] == 5 && showSchiffeInSpielerMap) {
					str = "  E";
				}
    			if(feld[j][i] == -1) {
    				str = "  X";
    			}
    			if(feld[j][i] == -2) {
    				str = "  o";
    			}
    			
    			this.listGegner.get(j).get(i).setText(str);
    		}
    	}
    }
    

}
