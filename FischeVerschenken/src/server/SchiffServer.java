package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchiffServer {
	static List<ClientConnection> serverThreads = Collections.synchronizedList(new ArrayList<ClientConnection>());
	private ServerSocket serverSocket;
	private int port;
	private ClientConnection gameConnection;
	private Socket connectionSocket = null;
	
	public SchiffServer(int port)
	{
		this.port = port;
	}
	
	public boolean starteServer()
	{
		// Waiting for clients

		System.out.println("Server Message: Waiting for connection on port " + port + ".");
		try
		{
			serverSocket = new ServerSocket(port);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Server Error: New Server socket Error!");
			return false;
		}
/*		finally
		{
			// Finally runs after the "endless while" for now, no need to stop
			// the servers main thread.
			if (serverSocket != null)
			{
				try
				{
					serverSocket.close();
					System.out.println("Server Message: serverSocket closed!");
				}
				catch (IOException ie)
				{
					System.out.println("Server Error: Error closing serverSocket.");
				}
			}
		}*/
	}

	public void warteAufSpieler() {
		while (true)
		{
			try
			{
				connectionSocket = serverSocket.accept();
				System.out.println("Server Message: Connection OK!");
				ServerThread clientThread = new ServerThread(connectionSocket);
				if (gameConnection == null)
				{
					gameConnection = new ClientConnection();
					gameConnection.playerA = clientThread;
					clientThread.setGameConni(gameConnection);
					sendeString("Warten auf Spieler.", clientThread.getClientSocket());
				}
				else if (gameConnection.playerB == null)
				{
					gameConnection.playerB = clientThread;
					clientThread.setGameConni(gameConnection);
					sendeString("OGOGOGOGO.", clientThread.getClientSocket());
					sendeString("Spieler gefunden. OGOGOGOGO.", gameConnection.playerA.getClientSocket());
					clientThread.start();
					gameConnection.playerA.start();
					synchronized (serverThreads)
					{
						serverThreads.add(gameConnection);
					}
				}
				else
				{
					gameConnection = new ClientConnection();
					gameConnection.playerA = clientThread;
					sendeString("Warten auf Spieler.", clientThread.getClientSocket());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.err.println("Server Error: Connection accept error!");
			}
		}
	}
	public void sendeString(String text, Socket connectionSocket)
	{
		OutputStream out;
		try
		{
			out = connectionSocket.getOutputStream();
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

	public void beendeServer()
	{
		try
		{
			serverSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
