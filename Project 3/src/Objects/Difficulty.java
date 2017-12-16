package Objects;

import java.awt.Color;
import java.io.FileReader;
import java.util.Scanner;

//NEED TO MAKE A LEVEL STOP TO CHANGE LEVELS AFTER****
public class Difficulty {

	private EZImage BckgrndPic;
	private static EZSound BckgrndMsc;
	private static EZText Level;
	private static EZText P;
	private static EZText Point;
	private static EZText T;
	private static EZText Timer;
	private static EZText S;
	private static EZText Lose;
	private static EZText Win;
	private static EZText bTxt;
	static EZRectangle button;

	protected static final int CENTX = 620;
	protected static final int CENTY = 376;
	private final int KIRBYY = 630;

	protected static boolean gameState = true;
	protected static long time;
	protected static String result;
	protected static int lvlPoints;
	static int clickX, clickY = 0;
	protected static boolean buttonClicked = false;

	// Declare and initialize a new Timer object
	static Timer timer = new Timer();

	Difficulty(String name, String pic, String msc, int ttlAmt, int ttlGd) {
		BckgrndPic = EZ.addImage(pic, CENTX - 118, CENTY);
		BckgrndMsc = EZ.addSound(msc);
		BckgrndMsc.loop();

		Level = EZ.addText("04B_30__.TTF", 1058, 100, "", Color.BLACK, 20);
		P = EZ.addText("04B_30__.TTF", 1074, 140, "Points:", Color.BLACK, 20);
		Point = EZ.addText("04B_30__.TTF", 1180, 140, "" + Object.points, Color.BLACK, 20);
		T = EZ.addText("04B_30__.TTF", 1106, 175, "Time Left: ", Color.BLACK, 20);
		Timer = EZ.addText("04B_30__.TTF", 1210, 175, "", Color.BLACK, 20);
		S = EZ.addText("04B_30__.TTF", 1180, 205, "seconds", Color.BLACK, 20);
		Lose = EZ.addText("04B_30__.TTF", 500, 286, "", Color.RED, 50);
		Win = EZ.addText("04B_30__.TTF", 500, 286, "", Color.BLUE, 50);

		// Make result text disappear
		Lose.setMsg("");
		Win.setMsg("");
		EZ.refreshScreen();

		// Declare and initialize a new Kirby object
		Kirby kirby = new Kirby(CENTX, KIRBYY);

		// Stores Level (Easy, Medium, or Hard)
		Level.setMsg(name);

		// Two arrays for good and bad objects to collect
		Object[] collect_objs = new Object[ttlAmt];
		Object[] bad_objs = new Object[ttlAmt];

		// Create a loop that will add the objects
		for (int i = 0; i < ttlGd; i++) {
			collect_objs[i] = new cake();
			collect_objs[i + ttlGd] = new lollipop();
			collect_objs[i + (ttlGd * 2)] = new maxtomato();
		}
		for (int i = 0; i < ttlAmt; i++) {
			bad_objs[i] = new spike();
		}

		// while loop that goes on if the game is being played controlled by
		// boolean variable gameState
		while (gameState == true) {
			timer.start(); // starts timer
			time = (30 - (timer.time_passed() / 1000)); // convert time
														// from
														// milliseconds
														// to
														// seconds
														// and count
														// down from
														// 30
			Timer.setMsg("" + time); // set message
										// for
										// amount of
										// time left
										// in game

			kirby.processStates();
			EZ.refreshScreen();

			// loops that makes the objects in the two arrays fall from the
			// top
			// and interact with Kirby if touched
			for (int i = 0; i < ttlAmt; i++) {
				collect_objs[i].go();
				bad_objs[i].go();
				collect_objs[i].interact();
				bad_objs[i].interact();
				//collect_objs[i].reappear();
				//bad_objs[i].reappear();
				lvlPoints = Object.points;
				Point.setMsg(lvlPoints + "");
			}

			if (Object.points == 5 || time == 0) {
				gameState = false;
			}
		} // close 'while'
		Win.setMsg("");
		EZ.refreshScreen();
	} // close 'Difficulty' constructor

	public static void reset() {
		timer.reset();
		Level.setMsg("");
		Point.setMsg("");
		Timer.setMsg("");
		Lose.setMsg("");
		gameState = true;
		buttonClicked = false;
		EZ.refreshScreen();
	}

	static void WinLvl() {
		// if number of points accumulated reaches 5, player wins and the game
		// stops
		if (Object.points == 5) {
			Win.setMsg("You Win");
			Win.pullToFront();
			result = "W";
			System.out.println(result);
			BckgrndMsc.stop();
			EZ.pause(2000);
			EZ.refreshScreen();
			gameState = false;
		}
	}

	static void LoseLvl() {
		// if amount of time runs out, player loses and the game stops
		if (time == 0) {
			Lose.setMsg("Game Over");
			Lose.pullToFront();
			BckgrndMsc.stop();
			result = "L";
			EZ.refreshScreen();
			gameState = false;
			PlayAgain();
		} // close 'if' when time = 0
	}

	static void WinGame() {
		if (Object.points == 5) {
			Win.setMsg("You Win The Game!!!!");
			Win.pullToFront();
			System.out.println(result);
			BckgrndMsc.stop();
			EZ.refreshScreen();
			gameState = false;
			PlayAgain();
		}
	}

	static void PlayAgain() {
		button = EZ.addRectangle(500, 400, 400, 70, new Color(199, 89, 128), true);
		bTxt = EZ.addText("04B_30__.TTF", 500, 400, "PLAY AGAIN?", Color.BLACK, 30);
		while (buttonClicked == false) {
			// Get the mouse's X and Y position
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();
			if (EZInteraction.wasMouseLeftButtonReleased()) {
				// If clickX and clickY is on top of my picture then...
				if (button.isPointInElement(clickX, clickY)) {
					buttonClicked = true;
					button.setColor(new Color(253, 192, 249));
					result = "A";
				}
			}
			EZ.refreshScreen();
		}
	}

}