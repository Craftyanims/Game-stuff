
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScore {
	public String name;
	public int highscore;
	public File file;
	
	HighScore() {
		this.file = new File("highScore.txt"); // creating the file
	}

	//Write into the file
	public void submitHighscore() {
		int score = Main.one.score;
		
		try {
			currentHighscore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (score > highscore) {
	
			// Write to the file, name and score
			Main.highscoreMessage += ("\n\n-NEW HIGHSCORE-");								// file
			try {
				PrintWriter output = new PrintWriter(file);
				output.println(Main.one.name);// Add the name to the
																// file
				output.println(Main.one.score);// Add the score to the
															// file
				
				output.close();
			} catch (IOException ex) {
				System.out.printf("ERROR: %s\n", ex);
			}
		}
	}
	public void resetHighscore(){
		Main.one.name = "";
		Main.one.score = 0;
		try {
			PrintWriter output = new PrintWriter(file);
			output.println(Main.one.name);// Add the name to the
															// file
			output.println(Main.one.score);// Add the score to the
			output.close();
		} catch (IOException ex) {
			System.out.printf("ERROR: %s\n", ex);
		}
	}

	//Read the high scores
	public void currentHighscore() throws FileNotFoundException {{

		Scanner input = new Scanner(file); // Get input from the file
		name = input.nextLine();
		highscore = Integer.parseInt(input.nextLine());
		input.close();
		//System.out.printf("Highest Score\nName: %s Score: %d\n", name, highscore);
		}
	}
}