package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import application.Main;
import gamelogic.SchiffMap;
import ui.ClientUI;

public class SchiffClient
{
	private Socket clientSocket;
	SchiffMap map = new SchiffMap();
	SchiffMap mapGegner = new SchiffMap();

	public SchiffMap getMap()
	{
		return map;
	}
	
	public SchiffMap getMapGegner()
	{
		return map;
	}
	
	/*
	 * verbindet den Clientsocket mit dem host an Port port. Liefert true, wenn die
	 * Verbindung aufgebaut wurde, sonst false.
	 */
	public boolean verbinden(String host, int port)
	{
		try
		{
			clientSocket = new Socket(host, port);
			if (!clientSocket.isConnected())
			{
				return false;
			}
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace(); // optional
			return false;
		}
	}

	// sendet text an Server
	public void senden(String text)
	{
		try
		{
			OutputStream out = clientSocket.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(text);
			writer.newLine();
			writer.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void sendeSchiessen()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Du darfst schiessen.");
			System.out.println("Gib die Koordinaten an! z.B.: A4");
			String koords = sc.nextLine();
			senden("Schuss " + koords);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void schuss(String msg)
	{
		// return 0: nichts getroffen, return 1: treffer, return 2: treffer versenkt,
		// return 3: alle versenkt
		String koords = msg.replace("Schuss ", "");
		int[] xy = new int[2];
		xy = parseKoords(koords);
		int treffer = map.schuss(xy[0], xy[1]);
		switch (treffer)
		{
		case 0:
			senden("Daneben du Nub! Kein Treffer.");
			this.mapGegner.setFeld(xy[0], xy[1], -2);
			break;
		case 1:
			senden("Treffer! Das hat so richtig BAM gemacht.");
			this.mapGegner.setFeld(xy[0], xy[1], -1);
			break;
		case 2:
			senden("Treffer versenkt. Blubb");
			this.mapGegner.setFeld(xy[0], xy[1], -1);
			break;
		case 3:
			senden("Treffer! Alles versenkt! Gewonnen!");
			this.mapGegner.setFeld(xy[0], xy[1], -1);
			break;
		}
	}

	/*
	 * wartet auf Nachrichteneingang (Zeichenkette) vom Server und liefert bei
	 * Empfang die Nachricht als Zeichenkette zur�ck.
	 */
	public String empfangen()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			return reader.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "BLYAT!!!";
		}
	}

	private boolean setzeSchiffe(int groesse)
	{

		Scanner sc = new Scanner(System.in);

		System.out.println("Setze dein " + groesse + "er Schiff");
		System.out.println("Soll das Schiff horizontal gesetzt werden? y/n");
		String hori = sc.nextLine();
		boolean horizontal;
		if (hori.equals("y"))
		{
			horizontal = true;
		}
		else
		{
			horizontal = false;
		}

		System.out.println("Gib die Koordinaten an! z.B.: A4");
		String koords = sc.nextLine();
		int[] xy = new int[2];
		xy = parseKoords(koords);

		if (map.setzeSchiffNeu(xy[0], xy[1], horizontal, groesse))
		{
			map.showMap();
			return true;
		}
		else
		{
			map.showMap();
			return false;
		}
	}

	public boolean stetzeAlleSchiffe()
	{
		boolean schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(4))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(3))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(3))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(2))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(2))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(2))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(1))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(1))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		schiffGesetzt = false;
		while (!schiffGesetzt)
		{
			if (setzeSchiffe(1))
			{
				System.out.println("Schiff wurde erfolgreich gesetzt!");
				schiffGesetzt = true;
			}
			else
			{
				System.out.println("Schiff konnte nicht gesetzt werden!");
			}
		}
		System.out.println("_______________________");
		schiffGesetzt = false;
		return true;
	}

	public int[] parseKoords(String koords)
	{
		int[] xy = new int[2];
		xy[0] = koords.toLowerCase().charAt(0) - 96;
		if (koords.length() == 2)
		{
			xy[1] = Integer.parseInt(koords.substring(1, 2));
		}
		else
		{
			xy[1] = Integer.parseInt(koords.substring(1, 3));
		}
		System.out.println(xy[0]);
		System.out.println(xy[1]);
		return xy;
	}

	/*
	 * meldet den Client ab, indem der Clientsocket geschlossen wird.
	 */
	public void abmelden()
	{
		try
		{
			clientSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
