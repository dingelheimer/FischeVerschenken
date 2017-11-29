package ui;

import java.util.Scanner;

import client.SchiffClient;

public class ClientUI
{
	public static void main(String[] args)
	{
		SchiffClient client = new SchiffClient();
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
				}
			}
		}
	}
}
