package ui;

import server.SchiffServer;

public class ServerUI
{
	public static void main(String[] args)
	{
		SchiffServer es = new SchiffServer(5000);
		if(es.starteServer()) {
			System.out.println("Warte auf Spieler! ...");
			es.warteAufSpieler();
			while(true) {
				
			}
		}
	}
}