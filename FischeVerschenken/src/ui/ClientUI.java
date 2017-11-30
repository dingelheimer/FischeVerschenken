package ui;

import java.util.Scanner;

import application.Main;
import client.SchiffClient;

public class ClientUI extends Thread
{
	SchiffClient client = new SchiffClient();
	

    
	@Override
	public void run() 
	{
		
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
				else if(message.contains("Treffer")) {
					switch (message) {
					case "Daneben du Nub! Kein Treffer." : 
						client.senden("Du bist dran!");
						break;
					case "Treffer! Das hat so richtig BAM gemacht.":
						client.sendeSchiessen();
						break;
					case "Treffer versenkt. Blubb":
						client.sendeSchiessen();
						break;
					case "Treffer! Alles versenkt! Gewonnen!":
						client.senden("Du hast verloren!");
						this.interrupt();
						break;
					}
				}
				else if (message.contains("Du hast verloren!")) {
					this.interrupt();
				}
				else if (message.contains("Du bist dran!")) {
					client.sendeSchiessen();
				}
			}
		}
	}
}
