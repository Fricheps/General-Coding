package Mitter;

import java.io.*;
import java.net.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MitterServer {
		
	static int port = 9999;
	String threadName;
	Thread t;
	static ServerSocket serverSocket;
	static Socket clientSocket;
	static boolean notifierConnected = false;
	
    public static void main(String[] args) throws UnknownHostException, IOException, JAXBException {
    	
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	while (true) {
    		try	{
	            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
	            clientSocket = serverSocket.accept();
	            
	            //Handle client request
	            System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
	            
	            //distinguish client & notifier
	            if (clientSocket.getPort() == 6666) {
	            	System.out.println("Connected to Notifier");
	            	//Treat client as notifier...
	            	notifierConnected = true;
	            }
	            
	            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	            
	    		JAXBContext jaxbContext = JAXBContext.newInstance(Notification.class);

	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		Notification notifier = (Notification) jaxbUnmarshaller.unmarshal(in);
	    		System.out.println(notifier);
	            
	            //Output reponse
	            System.out.println(in.readUTF());
	            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + clientSocket.getLocalSocketAddress() + "\nGoodbye!");
	            
	            clientSocket.close();
    		} 
    		catch(SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	        }
    		catch(IOException e) {
	            e.printStackTrace();
	            break;
	        }
    	}

    }
}
