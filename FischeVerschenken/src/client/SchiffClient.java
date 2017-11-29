package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import gamelogic.SchiffMap;

public class SchiffClient {
	private Socket clientSocket;
	SchiffMap map = new SchiffMap();

	/*
	 * verbindet den Clientsocket mit dem host an Port port. Liefert true, wenn die
	 * Verbindung aufgebaut wurde, sonst false.
	 */
	public boolean verbinden(String host, int port) {
		try {
			clientSocket = new Socket(host, port);
			if (!clientSocket.isConnected()) {
				return false;
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace(); // optional
			return false;
		}
	}

	// sendet text an Server
	public void senden(String text) {
		try {
			OutputStream out = clientSocket.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(text);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * wartet auf Nachrichteneingang (Zeichenkette) vom Server und liefert bei
	 * Empfang die Nachricht als Zeichenkette zur�ck.
	 */
	public String empfangen() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "BLYAT!!!";
		}
	}

	private boolean setzeSchiffe(int groesse) {
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Setze dein " + groesse + "er Schiff");
			System.out.println("Soll das Schiff horizontal gesetzt werden? y/n");
			String hori = sc.nextLine();
			boolean horizontal;
			if (hori.equals("y")) {
				horizontal = true;
			} else {
				horizontal = false;
			}

			System.out.println("Gib die Koordinaten an! z.B.: A4");
			String koords = sc.nextLine();
			int[] xy = new int[2];
			xy = parseKoords(koords);
			map.setzeSchiffNeu(xy[0], xy[1], horizontal, groesse);
			map.showMap();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean stetzeAlleSchiffe() {
		if (setzeSchiffe(4)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(3)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(3)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(2)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(2)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(2)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(1)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(1)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		if (setzeSchiffe(1)) {
			System.out.println("Schiff wurde erfolgreich gesetzt!");
		} else {
			System.out.println("Schiff konnte nicht gesetzt werden!");
		}
		return true;
	}

	public int[] parseKoords(String koords) {
		int[] xy = new int[2];
		xy[0] = koords.charAt(0) - 96;
		xy[1] = Integer.parseInt(koords.substring(1, 2));
		System.out.println(xy[0]);
		System.out.println(xy[1]);
		return xy;
	}

	/*
	 * meldet den Client ab, indem der Clientsocket geschlossen wird.
	 */
	public void abmelden() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
