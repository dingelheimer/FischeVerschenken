package ui;

import server.SchiffServer;

public class ServerUI
{
	public static void main(String[] args)
	{
		SchiffServer es = new SchiffServer(5000);
		es.starteServer();
	}
}