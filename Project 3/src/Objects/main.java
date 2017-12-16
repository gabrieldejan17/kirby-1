package Objects;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws java.io.IOException {

		// Setup EZ graphics system
		EZ.initialize(Difficulty.CENTX * 2, Difficulty.CENTY * 2);

		// Set background color to white
		EZ.setBackgroundColor(Color.WHITE);

		EZImage Panel;
		EZImage Instructions;
		EZSound WinMsc = EZ.addSound("WinGame.wav");
		EZText StartNote1;
		EZText StartNote2;
		EZText Restart;


		// Set up the buffered reader to read from the text file}
		Scanner difficulties = new Scanner(new FileReader("Difficulty.txt"));

		// Arrays for the data in file
		String[] level = new String[3];
		String[] back_pics = new String[3];
		String[] sound = new String[3];
		int[] arr_tot = new int[3];
		int[] ech_g = new int[3];
		// Read all data in file
		for (int i = 0; i < 3; i++) {
			level[i] = difficulties.next();
			arr_tot[i] = difficulties.nextInt();
			ech_g[i] = difficulties.nextInt();
			back_pics[i] = difficulties.next();
			sound[i] = difficulties.next();
		}
		difficulties.close();

		// Set panel background picture
		Panel = EZ.addImage("Panel_Bckgrnd.jpg", 1120, Difficulty.CENTY);
		Instructions = EZ.addImage("Panel_instructions.jpg", 1120, Difficulty.CENTY);
		StartNote1 = EZ.addText("04B_30__.TTF", 500, 500, "READ INSTRUCTIONS TO THE RIGHT ->", Color.BLACK, 25);
		StartNote2 = EZ.addText("04B_30__.TTF", 500, 570, "", Color.BLACK, 25);
		Restart = EZ.addText("04B_30__.TTF", 500, 570, "", Color.BLACK, 25);
		EZ.refreshScreen();
		for (int j = 0; j < 10; j++) {
			int s = 10 - j;
			StartNote2.setMsg("GAME STARTS IN " + s + " seconds");
			EZ.pause(1000);
			EZ.refreshScreen();
		}

		StartNote1.setMsg("");
		StartNote2.setMsg("");
		Instructions.hide();
		
		
		for (int i = 0; i < 3; i++) {
			if (Difficulty.result != "L") {
				Difficulty Levels = new Difficulty(level[i], back_pics[i], sound[i], arr_tot[i], ech_g[i]);
				EZ.refreshScreen();
				if (Difficulty.lvlPoints == 5 && i <= 1) {
					Difficulty.WinLvl();
					Difficulty.reset();
				} else {
					Difficulty.LoseLvl();
					if (Levels.buttonClicked == true) {
						i = -1;
						Difficulty.reset();
						EZ.refreshScreen();
					}
				}
				if (i == 2 && Difficulty.lvlPoints == 5) {
					Difficulty.WinGame();
					WinMsc.play();
					if (Levels.buttonClicked == true) {
						i = -1;
						Difficulty.reset();
						EZ.refreshScreen();
					}
				}
			}
		}

	}
}