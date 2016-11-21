import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	

	static Room[] rooms = new Room[19];
	static Scanner b = new Scanner(System.in);
	public static input B = new input();
	public static Player one = new Player();
	public static HighScore highscore = new HighScore();
	static boolean nameselection = true;
	static boolean GameRunning = true;
	static boolean rightInput = false;
	static String Response;
	static String state = "";
	static String breakPage = "------------------------------" + "\n++++++++++++++++++++++++++++++"
			+ "\n------------------------------";
	static String alotOfSpace = "\n\n\n\n\n\n\n";
	public static String highscoreMessage = "";

	public static void main(String[] args) {
while(1 != 2){
		state = "title screen";
		do {
			if (state.equalsIgnoreCase("Name selection")) {
				state = Beginning();
			} else if (state.equalsIgnoreCase("Tutorial")) {
				tutorial();
			} else if (state.equalsIgnoreCase("title screen")) {
				state = titleScreen();
			} else if (state.equalsIgnoreCase("Help")) {
				state = Help();
			}
		} while (!(state.equalsIgnoreCase("Game starting")));
		initalize();
		introducion();
		one.location = rooms[5];
		highscoreMessage = "";
		one.score = 10000;
		do { // rooms for game right here
			rightInput = false;
			System.out.println(breakPage + alotOfSpace + one.location);
			int sub = one.location.exits.length;
			String insert = b.nextLine();
			for (int i = 0; i < sub; i++) {
				if (insert.equalsIgnoreCase("SEARCH")) {// check to see if
					one.score -= 10;									// SEARCH was input
					one.location.search();
					System.out.println("-PRESS ENTER-");
					b.nextLine();
					break;
				}else if(insert.equalsIgnoreCase("LET ME FINISH THE GAME FAST")){
					one.location = rooms[8];
					break;
				}else if(insert.equalsIgnoreCase("reset highscore")){
					highscore.resetHighscore();
					System.out.println("highscore reset");
					break;
				} else if(insert.equalsIgnoreCase("BAG")){
					System.out.println("let's see... I currently have..");
					System.out.println(one.keyBag);
					System.out.println("-PRESS ENTER-");
					b.nextLine();
					break;
				}else if (insert.equalsIgnoreCase("MAP")) {
					one.score -= 10;
					settingUpMap();
					
					System.out.println("\n           _____________________________  __"
							+ "\n          |                     '       ==_3|"
							+ "\n          |         1           |_  2 __|    "
							+ "\n          |                  ____|    |___"
							+ "\n__________'-- - - - - - - __|             |___________________________________"
							+ "\n|         '              |6_       7       ______________8____________        |"
							+ "\n|    4    '        5        |___       ___|                           |   9   |"
							+ "\n|_________'_____________________|     |_______                        |__   __|"
							+ "\n|___ ________________|                         |                     ____|14|____"
							+ "\n|10 '____11__________                          |____________________|            |"
							+ "\n|_ _|       '        |            12            ___________13_______       15    | "
							+ "\n|           '        |                         |                |   |____________|"
							+ "\n|           '        |___________  ____________|                |"
							+ "\n|           '            '                     '                |"
							+ "\n|    16     '            '                     '        19      |"
							+ "\n|           '     17     '          18         '                |"
							+ "\n|           '            '                     '                |"
							+ "\n|___________'____________'_____________________'________________|"

							+ "\n\n--------------LEGEND--------------------------------------------" + "\n["
							+ rooms[0].currentlyInThis + "]1. Graveyard entry   [" + rooms[5].currentlyInThis
							+ "]6. Storage Room                     " + "\n[" + rooms[4].currentlyInThis
							+ "]5. Lower graveyard   [" + rooms[7].currentlyInThis
							+ "]8. Hallway 1                       " + "\n[" + rooms[3].currentlyInThis
							+ "]4. Upper graveyard   [" + rooms[10].currentlyInThis
							+ "]11.Hallway 2                        " + "\n[" + rooms[1].currentlyInThis
							+ "]2. Back Room         [" + rooms[12].currentlyInThis
							+ "]13.Hallway 3                      " + "\n[" + rooms[2].currentlyInThis
							+ "]3. Supply Closet     [" + rooms[13].currentlyInThis + "]14.Hallway 4 " + "\n["
							+ rooms[6].currentlyInThis + "]7. Drama Stage       [" + rooms[11].currentlyInThis
							+ "]12.Main Room  "

							+ "\n\n[" + rooms[9].currentlyInThis + "]10.Tool Shack" + "\n[" + rooms[8].currentlyInThis
							+ "]9. ? ? ?" + "\n[" + rooms[14].currentlyInThis + "]15.Dining Room" + "\n["
							+ rooms[15].currentlyInThis + "]16.Yard 1" + "\n[" + rooms[16].currentlyInThis
							+ "]17.Yard 2" + "\n[" + rooms[17].currentlyInThis + "]18.Entry Yard" + "\n["
							+ rooms[18].currentlyInThis + "]19.Yard 3");
					System.out.println("-PRESS ENTER-");
					b.nextLine();
					break;
				}

				if (insert.equalsIgnoreCase(one.location.exits[i].letter)// check to see if insert is equal to a valid exit
																
						&& one.location.exits[i].room.unlocked == true) {
					one.location = one.location.exits[i].room;
					break;// break out if so
				} else if (insert.equalsIgnoreCase(one.location.exits[i].letter)
						&& one.location.exits[i].room.unlocked == false) {
					System.out.println("\"hmm.. seems to be locked..\"\n-PRESS ENTER-");
					insert = b.nextLine();
					break;
				} else if (i == sub - 1) {
					System.out.println("that is not an option\n-PRESS ENTER");
					insert = b.nextLine();

				}

			}

		} while (one.location != rooms[8]);
		endScreen();
	}
	}

	public static void initalize() {
		// set up all the rooms
		rooms[0] = new Room("Graveyard entry", "You enter the graveyard, a sudden chill went"
				+ "\n down your spine. ", true, 0, 2, 0);
		rooms[1] = new Room("Back Room", "You see a bunch of old "
				+ "rusty tools everywhere.", true, 0, 3, 0);
		rooms[2] = new Room("Supply Closet", "You enter a cramp space with a lot of janitor\n"
				+ " tools and cleaning supplies.", false, 9, 1, 0);
		rooms[3] = new Room("Upper graveyard","You are in the last section of the graveyard\n"
				+ ". Just barely behind the tree's, you see water"
				+ ".",true,0,1,0);
		rooms[4] = new Room("Lower graveyard","You move down the graveyard seeing more graves\n"
				+ "then you thought you would ever see.",false,8,2,0);
		rooms[5] = new Room("Storage Room","You enter a small space "
				+ "filled with shelves and boxes",true,6,1,0);//starting room
		rooms[6] = new Room("Drama Stage","You enter a large room with old ripped curtains dividing \n"
				+ "the main stage, with the audience.",false,0,4,0);
		rooms[7] = new Room("Hallway 1","You enter a hallway with"
				+ " various of paintings hanging on the "
				+ "wall",true,0,2,0);
		rooms[8] = new Room("? ? ?"," ",false,0,2,0);//ending room
		rooms[9] = new Room("Tool Shack","You enter a small rooms filled with "
				+ "garden tools.",false,4,2,0);
		rooms[10] = new Room("Hallway 2","You enter a long hallway with nothing but darkness down\n"
				+ "the way.",true,17,2,0);
		rooms[11] = new Room("Main Room","You enter a large room with a nice "
				+ "chandelier ",true,0,4,0);
		rooms[12] = new Room("Hallway 3","You enter a older looking hallway with wallpaper\n"
				+ "peeling from the wall",true,0,2,0);
		rooms[13] = new Room("Hallway 4","You enter a long hallway with a long "
				+ "painting hanging from the wall.",true,0,2,0);
		rooms[14] = new Room("Dining Room","You see a long table filled with silver ware and plates.\n"
				+ "almost as if people were just about to eat."
				,true,0,2,0);
		rooms[15] = new Room("Yard 1","You move a little to see a room follow "
				+ "by a garden.",true,0,2,0);
		rooms[16] = new Room("Yard 2","You move a little to see a nice tire swing. "
				+ "Must be popular with kids",true,0,2,0);
		rooms[17] = new Room("Entry Yard","You see a long pathway leading from "
				+ "a blocked off gate.",false,0,3,0);
		rooms[18] = new Room("Yard 3","You move a little to see leaves "
				+ "everywhere",true,2,1,0);

		
		
		rooms[0].exits[0] = new Exit("a", rooms[1]);
		rooms[0].exits[1] = new Exit("b", rooms[4]);
		rooms[1].exits[0] = new Exit("a", rooms[2]);
		rooms[1].exits[1] = new Exit("b", rooms[0]);
		rooms[1].exits[2] = new Exit("c", rooms[6]);
		rooms[2].exits[0] = new Exit("a", rooms[1]);
		rooms[3].exits[0] = new Exit("a",rooms[4]);
		rooms[4].exits[0] = new Exit("a",rooms[3]);
		rooms[4].exits[1] = new Exit("b",rooms[0]);
		rooms[5].exits[0] = new Exit("a",rooms[6]);
		rooms[6].exits[0] = new Exit("a",rooms[1]);
		rooms[6].exits[1] = new Exit("b",rooms[5]);
		rooms[6].exits[2] = new Exit("c",rooms[7]);
		rooms[6].exits[3] = new Exit("d",rooms[11]);
		rooms[7].exits[0] = new Exit("a",rooms[6]);
		rooms[7].exits[1] = new Exit("b",rooms[8]);
		rooms[9].exits[0] = new Exit("a",rooms[10]);
		rooms[9].exits[1] = new Exit("b",rooms[15]);
		rooms[10].exits[0] = new Exit("a",rooms[9]);
		rooms[10].exits[1] = new Exit("b",rooms[11]);
		rooms[11].exits[0] = new Exit("a",rooms[10]);
		rooms[11].exits[1] = new Exit("b",rooms[6]);
		rooms[11].exits[2] = new Exit("c",rooms[12]);
		rooms[11].exits[3] = new Exit("d",rooms[17]);
		rooms[12].exits[0] = new Exit("a",rooms[11]);
		rooms[12].exits[1] = new Exit("b",rooms[14]);
		rooms[13].exits[0] = new Exit("a",rooms[8]);
		rooms[13].exits[1] = new Exit("b",rooms[14]);
		rooms[14].exits[0] = new Exit("a",rooms[12]);
		rooms[14].exits[1] = new Exit("b",rooms[13]);
		rooms[15].exits[0] = new Exit("a",rooms[9]);
		rooms[15].exits[1] = new Exit("b",rooms[16]);
		rooms[16].exits[0] = new Exit("a",rooms[15]);
		rooms[16].exits[1] = new Exit("b",rooms[17]);
		rooms[17].exits[0] = new Exit("a",rooms[16]);
		rooms[17].exits[1] = new Exit("b",rooms[11]);
		rooms[17].exits[2] = new Exit("c",rooms[18]);
		rooms[18].exits[0] = new Exit("a",rooms[17]);
	}

	public static void settingUpMap() {
		for (int i = 0; i < rooms.length; i++) {
			rooms[i].currentlyInThis = " ";
			if(rooms[i] == one.location) rooms[i].currentlyInThis = "X";
		}
        
	}
	
	public static void endScreen(){
		String[]page = new String[3];
		page[0] = "You are now outside\n"
				+ "The light blinds you for a bit since you were inside for so long."
				+ "\n\n";
		page[1] = "You see that you are on a beach shore, with a lighthouse a little further down."
				+ "\nYou begin to realize you have no idea to why you are here "
				+ "\n\n";
		page[2] = "You begin to miss the trapped space of the house and feel empty without it.\n"
				+ "You now wonder if you should go back... Live there..."
				+ "\nShould you go back?"
				+ "\n\n| a | GO BACK"
				+ "\n\n| b | DON’T GO BACK";
		int currentPage = 0;
		String insert;
		do {
			System.out.println(page[currentPage]);
			if(currentPage < page.length-1)System.out.println("-PRESS ENTER-");
			insert = b.nextLine();
			if(currentPage < page.length-1)currentPage++;
		} while (!(insert.equalsIgnoreCase("A")||insert.equalsIgnoreCase("B")));
		if(insert.equalsIgnoreCase("A")){
			System.out.println("“Well.. I guess it’s not too bad in there...”"
					+ "\nYou then enter the house.. Resetting all the locked rooms,"
					+ "\n placing all the keys back into the exact spot he had found them "
					+ "\nto begin with.. He then enters the very room he began in and waited..."
					+ "\nWaited for when he will forget everything..."
					+ "\n\nAgain..."
					+ "\n\n-PRESS ENTER-");
			one.endingChoice = "CONTINUING THE LOOP";
			b.nextLine();
		}else if(insert.equalsIgnoreCase("B")){
			System.out.println("“Well.. I guess I should go explore the world”"
					+ "\n\nYou set off towards the lighthouse only to find a \n"
					+ "raft awaiting you as if it was meant to be placed there. \n"
					+ "You set yourself inside the raft and push away into the \n"
					+ "never ending sea. Your main goal is to find land else from here..\n"
					+ "\nand reunit with society..."
					+ "\n\n-PRESS ENTER");
			one.endingChoice = "SET FREE";
			b.nextLine();
		}
		highscore.submitHighscore();
		try {
			highscore.currentHighscore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(one.score > highscore.highscore) highscoreMessage += "\n\n -NEW HIGHSCORE-";
		
		highscoreMessage += "\n\nCurrent Highscore: " + highscore.highscore + "E"
				+ "\nCurrent Holder: "+ highscore.name;
		System.out.println("-END SCORE-"
				+ "\nNAME: " + one.name
				+ "\nSCORE: "+ one.score + "E"
				+ "\nENDING CHOICE: " + one.endingChoice
				+highscoreMessage
				+ "\n\n-Press Enter to return to the home page-");
		b.nextLine();
		
	}

	public static String titleScreen() {
		System.out.println(breakPage);
		System.out.println("Typing Roaming\n  PLAY    HELP" + alotOfSpace + alotOfSpace + alotOfSpace);
		String Response = B.AvalOption("PLAY", "PLAY", "HELP", "HELP");
		if (Response.equalsIgnoreCase("PLAY")) {
			return "Game starting";
		} else if (Response.equalsIgnoreCase("HELP")) {
			return "Help";
		}

		return "title screen";
	}

	public static String Help() {
		String[] helpPage = new String[4];
		int currentPage = 0;
		helpPage[0] = breakPage + "\nHELP: history" + "\n_________"
				+ "\nTYPING ROAM is a puzzle roaming game done with text. I’ve tried to "
				+ "\nimplement the key feature in a proper roaming game into text but doing "
				+ "\nso had to eliminate some other features you would suspect." + "\n\n" + "-next-" + alotOfSpace;
		helpPage[1] = breakPage + "\nHELP: commands" + "\n_________" + "\n-SEARCH-"
				+ "\nType this command in any given room to attempt to find something."
				+ "\n Keep in mind like an actual roaming game, you would probably not find the "
				+ "\nitem needed with one search scan." + "\n\n" + "\n-back-" + "\n-next-" + alotOfSpace;
		helpPage[2] = breakPage + "\nHELP: commands" + "\n_________" + "\n-MAP-"
				+ "\nType this command in any given room to see the map of the entire location and "
				+ "\nwhere you are located. Keep in mind you will not see which rooms are locked or "
				+ "\nnot, but at least you are given the map." + "\n\n" + "\n-back-" + "\n-next-" + alotOfSpace;
		helpPage[3] = breakPage + "\nHELP: commands "
				+ " \n_________ "
				+ "\n-BAG-"
				+ "\nType this command to see all the keys you have found this game,"
				+ "\nkeep in mind that even after you use a key. That will still be "
				+ "\n be present in your bag"
				+ "\n-back-"
				+ "\n-next-" + alotOfSpace;
		do {
			System.out.println(helpPage[currentPage]);
			String Response = B.AvalOption("Next", "Back", "Next", "Back");
			if (Response.equalsIgnoreCase("NEXT")) {
				currentPage += 1;
			} else if (Response.equalsIgnoreCase("BACK") && (currentPage != 0)) {
				currentPage -= 1;
			}
		} while (currentPage <= helpPage.length);

		return "title screen";

	}

	public static void introducion() {
		String insert;
		String input;
		String[] page = new String[3];
		page[0] = "It was dark.. Why was it dark?" + "\n\n. . ." + "\n\nIt was cold.. Why was it cold?"
				+ "\n\n. . ." + "\n\nWhere am I..?  Who am I?";
		page[1] = "It has come to your attention that you are in a room... "
				+ "\nA relatively big room. You don’t know why you are here and "
				+ "\nyou’re struggling to remember...";
		page[2] = "“Where am I? Who am I?” \n\nYou begin to search your brain"
				+ "\n to find any clue as to who you are.";
		int currentPage = 0;
		do {
			System.out.println(page[currentPage]);
			System.out.println("-PRESS ENTER-");
			b.nextLine();
			currentPage++;
		} while (currentPage < page.length);
		System.out.println("“M-my.. my name is..?”");
		do {
			System.out.println("\n\n-INSERT YOUR NAME-");
			insert = b.nextLine();
			System.out.println("“My..”\n\n“Name is.. [" + insert + "]?”\n\n- YES/NO  -");
			input = b.nextLine();
			if (input.equalsIgnoreCase("NO")) {
				System.out.println("“No no.. that’s not my name.. My name was..?”\n-PRESS ENTER-");
				b.nextLine();
			}else if(input.equalsIgnoreCase("YES")){	
			}else{
				System.out.println("“No no.. i must have a name..”\n-PRESS ENTER-");
				b.nextLine();
			}
		} while (!(input.equalsIgnoreCase("YES")));
		System.out.println("“Y-yes.. [" + insert + "]. That’s my name!”" + "\n\n“Now to find my way out of here..”"
				+ "\n\n-PRESS ENTER-");
		one.name = insert;
		b.nextLine();
	}

	public static String Beginning() {
		String i;
		do {
			System.out.println(breakPage + "\ninsert name" + alotOfSpace);
			one.name = b.nextLine().toUpperCase();
			System.out.println(breakPage + one.name + " \nis your name correct?\nY       N" + alotOfSpace);
			i = b.nextLine();
			if (i.equalsIgnoreCase("Y")) {
				System.out
						.println(breakPage + "\nok your name has been saved, enjoy the game " + one.name + alotOfSpace);
			} else if (i.equalsIgnoreCase("N")) {

			}
		} while (!(i.equalsIgnoreCase("y")));
		i = "";
		do {
			System.out.println(breakPage + "would you like to go through the tutorial?\nY         N" + alotOfSpace);
			i = b.nextLine();
			if (i.equalsIgnoreCase("Y")) {
				return "Tutorial";
			} else {
				return "Introduction";
			}
		} while (!(i.equalsIgnoreCase("Y")));

	}

	public static void tutorial() {
		System.out.println("type in a, b ,c , or d");
		String Response = B.AvalOption("a", "b", "c", "d");
		if (Response.equalsIgnoreCase("A")) {
			System.out.println("hooiiii a ");
		} else if (Response.equalsIgnoreCase("B")) {
			System.out.println("sup b");
		} else if (Response.equalsIgnoreCase("C")) {
			System.out.println("waaazzzaaaaaaaap c");
		} else if (Response.equalsIgnoreCase("D")) {
			System.out.println("dope d");
		}
	}

}
