package Mitter;

//Non-human notifications, automated through UniAdelaide building management/tech department
//database[][] represent the list of available messages that can be sent automatically
public class EmbeddedSystem extends Notification {
	
	public void init (String id, String loc, String name, int alertCode) {
		
		this.setSender(id);
		this.setLocation(loc);
		
		String database[][] = {	{"Elevator " + name + " is currently down for maintenance.", "Notice"},
								{"Fire alarm has been activated in " + loc + ". Please leave the building immediately.", "Urgent"},
								{"Extreme winds detected, please be cautious", "Caution"}};
		
		if (alertCode <= database.length && alertCode != 0) {
			this.setMessage(database[alertCode-1][0]);
			this.setSeverity(database[alertCode-1][1]);
			//this.setTimestamp(dateFormat.format(date));
		}
		else {
			System.out.println("Check your alert code again!");
			return;
		}
	}
}
