package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SchiffClient
{
private Socket clientSocket;
	

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

	/*
	 * wartet auf Nachrichteneingang (Zeichenkette) vom Server und liefert bei
	 * Empfang die Nachricht als Zeichenkette zurück.
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
