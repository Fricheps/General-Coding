package Mitter;

import java.io.*;
import java.net.*;

public class MitterClient {
	
	static Socket clientSocket;
	static DataOutputStream os;
	static DataInputStream is;
	private static Socket client;
	
	public static void main(String[] args) throws IOException {
		String serverName = "localhost";
		int port = Integer.parseInt(args[0]);
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			client = new Socket(serverName, port);
			
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
 
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
 
			System.out.println("Server says " + in.readUTF());
			client.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}