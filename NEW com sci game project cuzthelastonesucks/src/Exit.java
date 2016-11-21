
public class Exit {
String roomName;
String letter;
int goToRoom;
Room room;


public Exit(String letter, Room room){
	this.letter = letter;
	this.roomName = room.roomName;
	this.room = room;
}

public Exit(){
	
}
public String toString(){
	return "\n | " + letter + " | "  + roomName;
}
	
	
	
}
