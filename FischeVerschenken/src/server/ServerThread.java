package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread
{
	private Socket clientSocket;
	private ClientConnection gameConni;
	public boolean rdyFlag = false;
	public boolean playerFlagA;

	public ServerThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run()
	{
		try
		{
			OutputStream out;
			if (clientSocket.equals(gameConni.playerA.getClientSocket()))
			{

				out = gameConni.playerB.getClientSocket().getOutputStream();
				playerFlagA = true;
			}
			else
			{
				out = gameConni.playerA.getClientSocket().getOutputStream();
				playerFlagA = false;
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (clientSocket.isConnected())
			{
				String msg = reader.readLine();
				if (msg != null)
				{
					if (msg.contains("Schuss"))
					{
						writer.write(msg);
						writer.newLine();
						writer.flush();
					}
					else if (msg.contains("Schiffe gesetzt"))
					{
						rdyFlag = true;
						boolean notStart = true;
						if (playerFlagA)
						{
							writer.write("Warte auf anderen Spieler...");
							writer.newLine();
							writer.flush();
							while (notStart)
							{
								notStart = !gameConni.playerB.rdyFlag;
								try
								{
									this.sleep(200);
								}
								catch (InterruptedException e)
								{
									// e.printStackTrace();
								}
							}
						}
						else
						{
							writer.write("Warte auf anderen Spieler...");
							writer.newLine();
							writer.flush();
							while (notStart)
							{
								notStart = !gameConni.playerA.rdyFlag;
								try
								{
									this.sleep(200);
								}
								catch (InterruptedException e)
								{
									// e.printStackTrace();
								}
							}
						}
						writer.write("Alle Spieler bereit");
						writer.newLine();
						writer.flush();
					}
					else if (msg.contains("Treffer"))
					{
						writer.write(msg);
						writer.newLine();
						writer.flush();
					}
					else if (msg.contains("Du hast verloren!"))
					{
						writer.write(msg);
						writer.newLine();
						writer.flush();
					}
					else if (msg.contains("Du bist dran"))
					{
						writer.write(msg);
						writer.newLine();
						writer.flush();
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ClientConnection getGameConni()
	{
		return gameConni;
	}

	public void setGameConni(ClientConnection gameConni)
	{
		this.gameConni = gameConni;
	}

	public Socket getClientSocket()
	{
		return clientSocket;
	}
}
