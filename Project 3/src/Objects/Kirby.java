package Objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Kirby {
	
	//declare four EZImage type variables for Kirby's states (stand, move right, move left, and inhale)
	EZImage Stand;
	EZImage MoveRight;
	EZImage MoveLeft;
	EZImage Inhale;
	
	//position variables that are shared throughout all instances of this class
	static int posx = 0;
	static int posy = 0;

	//assigns a state to an integer for the switch statement below
	static final int STAND = 1;
	static final int MOVERIGHT = 2;
	static final int MOVELEFT = 3;
	static final int INHALE = 4;
	ArrayList<String> states = new ArrayList<String>();		//an array list of Kirby's states

	int KirbyState = STAND;

	static final int MDIST = 1000;	//max distance Kirby can travel horizontally

	int moveCounter = 0;

	boolean state = true;

	//Kirby's Constructor
	public Kirby(int x, int y) {
		//Kirby's x and y coordinates
		posx = x;
		posy = y;

		//initializes four EZImage type variables for Kirby's states
		Stand = EZ.addImage("Kirby_Stand.png", posx, posy);
		MoveRight = EZ.addImage("Kirby_Right.png", posx, posy);
		MoveLeft = EZ.addImage("Kirby_Left.png", posx, posy);
		Inhale = EZ.addImage("Kirby_Tornado.png", posx, posy);

		//initially shows Kirby in 'Stand' state
		hideKirby();
		Stand.show();
	}

	//hides Kirby's state(s)
	void hideKirby() {
		Stand.hide();
		MoveRight.hide();
		MoveLeft.hide();
		Inhale.hide();
	}
	
	void removeKirby() {
		Stand.translateTo(-100, -100);
		MoveRight.translateTo(-100, -100);
		MoveLeft.translateTo(-100, -100);
		Inhale.translateTo(-100, -100);
	}

	//translates
	void positionKirby(int x, int y) {
		Stand.translateTo(x, y);
		MoveRight.translateTo(x, y);
		MoveLeft.translateTo(x, y);
		Inhale.translateTo(x, y);
	}

	//Kirby's states controlled by player's keyboard input in switch statement
	public boolean processStates() {

		switch (KirbyState) {
		//Stand state
		case STAND:
			//maps to 'inhale' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
				KirbyState = INHALE;
				hideKirby();
				Inhale.show();
			}
			//maps to 'move right' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_RIGHT) && posx <= MDIST) {
				KirbyState = MOVERIGHT;
				hideKirby();
				MoveRight.show();
			}
			//maps to 'move left' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_LEFT) && posx >= 0) {
				KirbyState = MOVELEFT;
				hideKirby();
				MoveLeft.show();
			}
			break;
		//Move right state
		case MOVERIGHT:
			if (posx <= MDIST - 50) {
				posx += 10;
				moveCounter++;
			}
			if (moveCounter > MDIST) {
				KirbyState = MOVERIGHT;
				hideKirby();
				MoveRight.show();
			} else {
				//maps to 'stand' state after right arrow key is released
				if (EZInteraction.wasKeyReleased(KeyEvent.VK_RIGHT)) {
					KirbyState = STAND;
					hideKirby();
					Stand.show();
				} else {
					positionKirby(posx, posy);
					//
					//maps to 'inhale' state
					if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
						KirbyState = INHALE;
						hideKirby();
						Inhale.show();
					}
				}
			}
			//maps to 'move left' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_LEFT) && posx >= 0) {
				KirbyState = MOVELEFT;
				hideKirby();
				MoveLeft.show();
			}
			//maps to 'inhale' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
				KirbyState = INHALE;
				hideKirby();
				Inhale.show();
			}
			break;
		//'Move left' state
		case MOVELEFT:
			if (posx >= 0 + 50) {
				posx -= 10;
				moveCounter--;
			}
			if (MDIST - moveCounter <= 0) {
				KirbyState = MOVELEFT;
				hideKirby();
				MoveLeft.show();
			} else {
				//maps to 'stand' state after left key arrow is released
				if (EZInteraction.wasKeyReleased(KeyEvent.VK_LEFT)) {
					KirbyState = STAND;
					hideKirby();
					Stand.show();
				} else {
					positionKirby(posx, posy);
					//maps to 'inhale' state
					if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
						KirbyState = INHALE;
						hideKirby();
						Inhale.show();
					}
				}
			}
			//maps to 'move right' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_RIGHT) && posx <= MDIST) {
				KirbyState = MOVERIGHT;
				hideKirby();
				MoveRight.show();
			}
			//maps to 'inhale' state
			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
				KirbyState = INHALE;
				hideKirby();
				Inhale.show();
			}
			break;
		//'Inhale' state
		case INHALE:
			//after spacebar is released, Kirby is at 'stand' state
			if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				KirbyState = STAND;
				hideKirby();
				Stand.show();
			}
			break;
		}
		return true;
	}

}