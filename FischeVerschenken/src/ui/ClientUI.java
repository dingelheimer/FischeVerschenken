package ui;

import java.util.Scanner;

import application.Main;
import client.SchiffClient;

public class ClientUI extends Thread
{
	SchiffClient client = new SchiffClient();
	int[] letzterSchuss = new int[2];
	
	
	public SchiffClient getClient()
	{
		return client;
	}

	@Override
	public void run() 
	{
		
		Scanner sc = new Scanner(System.in);
		
		if (client.verbinden("172.16.224.151", 5000))
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
					String koords = client.sendeSchiessen();
					letzterSchuss = client.parseKoords(koords);
				}
				else if (message.contains("Schuss")) {
					client.schuss(message);
				}
				else if(message.contains("Treffer")) {
					String koords;
					switch (message) {
					case "Daneben du Nub! Kein Treffer." : 
						this.client.getMapGegner().setFeld(letzterSchuss[0], letzterSchuss[1], -2);
						System.out.println("Dein Gegner ist dran.");
						client.senden("Du bist dran!");
						break;
					case "Treffer! Das hat so richtig BAM gemacht.":
						this.client.getMapGegner().setFeld(letzterSchuss[0], letzterSchuss[1], -1);
						 koords = client.sendeSchiessen();
						 letzterSchuss = client.parseKoords(koords);
						break;
					case "Treffer versenkt. Blubb":
						this.client.getMapGegner().setFeld(letzterSchuss[0], letzterSchuss[1], -1);
						 koords = client.sendeSchiessen();
						 letzterSchuss = client.parseKoords(koords);
						break;
					case "Treffer! Alles versenkt! Gewonnen!":
						this.client.getMapGegner().setFeld(letzterSchuss[0], letzterSchuss[1], -1);
						client.senden("Du hast verloren!");
						this.interrupt();
						client.abmelden();
						break;
					}
				}
				else if (message.contains("Du hast verloren!")) {
					this.interrupt();
					client.abmelden();
				}
				else if (message.contains("Du bist dran!")) {
					client.sendeSchiessen();
				}
			}
		}
	}
}
