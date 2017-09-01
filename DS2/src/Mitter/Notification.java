package Mitter;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Base class for XML Object Implementation
@XmlRootElement
public class Notification {

	DateFormat dateFormat;
	Date date;
	String sender;
	String location;
	String message;
	String timestamp;
	String severity;
	int id;

	Notification() {
		sender = "";
		location = "";
		message = "";
		timestamp = "";
		severity = "";
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		timestamp = dateFormat.format(date);
	}
	
	//For embedded system or automatic notifications
	public void init(String id, String loc, String name, int alertCode) {
	}
	
	public String getSender() {
		return this.sender;
	}
	
	@XmlElement
	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getLocation() {
		return this.location;
	}
	
	@XmlElement
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getMessage() {
		return this.message;
	}	

	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	@XmlElement
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSeverity() {
		return this.severity;
	}
	
	@XmlElement
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public int getId( ) {
		return id;
	}
	
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
}
