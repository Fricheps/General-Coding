package Mitter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

//Handles all notifications through here and
//establish connection with the server.
public class Notifier {
	
	static int id = 0;
	private static Socket notifier;
	
	public static void main(String[] args) throws IOException {
		
		//Testing area...
		//1 for elevator maintenance, 2 for fire alarm, 3 for extreme wind
		Notification embed = new EmbeddedSystem();
		embed.init("UniAdel Elevator System", "Ingkarni Wardli", "IW01", 1);
		embed.init("UniAdel Fire Department", "Benham Building", "", 2);
		/*Notification admin = new Notification();
		admin.setId(100);
		admin.setSender("Tech Support/IT");
		admin.setMessage("Access Adelaide will be shut down for maintenance, at 20/2/2002 12:10PM");
		admin.setLocation("IT Department");
		admin.setSeverity("Notice");*/
		
		//Serialize Object to XML
		/*try {
			File file = new File("C:\\Users\\frich\\Desktop\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Notification.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(embed, file);
			jaxbMarshaller.marshal(embed, System.out);
			jaxbMarshaller.marshal(admin, System.out);
		}*/

		
		String serverName = "localhost";
		int port = Integer.parseInt(args[0]);
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			notifier = new Socket(serverName, port, null, 6666);
			
			System.out.println("Just connected to " + notifier.getRemoteSocketAddress());
			
			OutputStream outToServer = notifier.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Notification.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(embed, out);

			//out.writeUTF("Hello from " + notifier.getLocalSocketAddress());
			InputStream inFromServer = notifier.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
 
			System.out.println("Server says " + in.readUTF());
			notifier.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
