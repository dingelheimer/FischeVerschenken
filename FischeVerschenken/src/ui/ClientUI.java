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
				System.out.println(client.empfangen()); 
				
				client.stetzeAlleSchiffe();
			}
		}
	}
}
