import java.util.Arrays;
import java.util.Scanner;

public class Room {
	static Scanner b = new Scanner(System.in);
	String description;
	Exit[] exits;
	boolean unlocked = false;
	boolean locked = false;
	int keyToRoom;
	boolean firstTimeInRoom = true;
	String[] flavourText;
	String roomName;
	String currentlyInThis = " ";
	public void search() {
		int rng = (int) (Math.random() * 3);
		if (rng == 1 && keyToRoom != 0) {
			Main.rooms[keyToRoom].unlocked = true;
			System.out.println("You have found the key with the labels \n["
					+ "" + Main.rooms[keyToRoom].roomName + "] on it.");
			Main.one.keyBag.add(Main.rooms[keyToRoom].roomName + " Key");
			keyToRoom = 0;
		} else {
			System.out.println("you have found nothing");
		}
	}

	public Room(String roomName, String description, boolean unlocked, int keyToRoom, int amountofExit, int page) {
		this.roomName = roomName;
		this.description = description;
		this.unlocked = unlocked;
		locked = unlocked;
		this.keyToRoom = keyToRoom;
		exits = new Exit[amountofExit];
		flavourText = new String[page];
	}

	public Room() {

	}

	public String toString() {// overriding the toString() method
		if (firstTimeInRoom == true && flavourText.length < 0) {
			int currentPage = 0;
			do {
				System.out.println(flavourText[currentPage]);
				System.out.println("-PRESS ENTER-");
				b.nextLine();
				currentPage++;
			} while (currentPage < flavourText.length);

		}
		if(unlocked != locked && firstTimeInRoom == true){
			System.out.println("Its unlocked!\n-PRESS ENTER-");
			b.nextLine();
		}
		firstTimeInRoom = false;
		return roomName + "\n" + description + "\n" + Arrays.toString(exits);
	}
}
