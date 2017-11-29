package ui;

import java.util.Scanner;

import application.Main;
import client.SchiffClient;

public class ClientUI extends Thread
{
	private Main main;
    
    public void setMain(Main main) {
		this.main = main;
	}
	

    
	@Override
	public void run() 
	{
		SchiffClient client = new SchiffClient(this.main);
		Scanner sc = new Scanner(System.in);
		
		if (client.verbinden("localhost", 5000))
		{
			while(true) {
				String message = client.empfangen();
				System.out.println(message); 
				if(message.contains("OGOGOGOGO")) {
					
					System.out.println("Zweiter Spieler gefunden!");
					if(client.stetzeAlleSchiffe()) {
						client.senden("Schiffe gesetzt");
					}
				}else if (message.contains("Lasset die Spiele beginnen! Du kannst")) {
					client.sendeSchiessen();
				}
				else if (message.contains("Schuss")) {
					client.schuss(message);
				}
			}
		}
	}
}
