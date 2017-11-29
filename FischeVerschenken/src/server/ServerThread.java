package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket clientSocket;
	private ClientConnection gameConni;
	public boolean rdyFlag = false;
	public boolean playerFlagA;

	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			OutputStream out;
			if (clientSocket.equals(gameConni.playerA.getClientSocket())) {

				out = gameConni.playerB.getClientSocket().getOutputStream();
				playerFlagA = true;
			} else {
				out = gameConni.playerA.getClientSocket().getOutputStream();
				playerFlagA = false;
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (clientSocket.isConnected()) {
				String msg = reader.readLine();
				if (msg != null) {
					if (msg.contains("Schuss")) {
						writer.write(msg);
						writer.newLine();
						writer.flush();
					} else if (msg.contains("Schiffe gesetzt")) {
						rdyFlag = true;
						if(playerFlagA) {
							writer.write("Warte auf anderen Spieler...");
							writer.newLine();
							writer.flush();
							while(!gameConni.playerB.rdyFlag);
						}else {
							writer.write("Warte auf anderen Spieler...");
							writer.newLine();
							writer.flush();
							while(!gameConni.playerA.rdyFlag);
						}
						writer.write("Alle Spieler bereit");
						writer.newLine();
						writer.flush();
					}
					else if (msg.contains("Treffer")) {
						writer.write(msg);
						writer.newLine();
						writer.flush();
						
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void empfangeKoords(String koords) {
		int x = koords.charAt(0) - 97;
		int y = Integer.parseInt(koords.substring(1, 2)) - 1;
	}

	public ClientConnection getGameConni() {
		return gameConni;
	}

	public void setGameConni(ClientConnection gameConni) {
		this.gameConni = gameConni;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
}
