package Objects;


import java.awt.event.KeyEvent;
import java.util.Random;

public class Object {

	// create a static final random generator rg
	final static Random rg = new Random();

	// Member variables
	EZImage image;
	int speed;
	static int points; 
	boolean active;
	
	int x;
	int y;
	int posy;

	// Constructor
	Object(String fname) {

		// set speed to a random value between 1 and 5
		speed = rg.nextInt(4) + 1;
		// set x to a random number between 0 and 1000
		x = rg.nextInt(950);
		// set y to a random number between -752 and 0
		y = rg.nextInt(752) - 752;
		// set points to 0
		points = 0;
		// add image
		image = EZ.addImage(fname, x, y);
		// set active to true
		active = true;		
	}

	// Member functions
	void go() {
		// If object is active, advance y by speed amount and translate image to
		// x, y
		if (active == true) {
			y += speed;
			image.translateTo(x, y);
		}
	}
	void reset() {
		points = 0;
	}
	void hide() {
		image.translateTo(-100,-100);
	}
	int interact(){
		// if spacebar is down and the image is touching the Kirby object, move the image to -100, -100
		// set active to false, minus 1 from points and return points
		if (image.isPointInElement(Kirby.posx ,Kirby.posy) && image.getWidth()==100) {
			image.translateTo(-100, -100);
			active = false;			
			points = points-1; 
			return points;
		}
		// if spacebar is down and the image is touching the Kirby object, move the image to -100, -100
		// set active to false, add 1 from points and return points
		if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE) && image.isPointInElement(Kirby.posx ,Kirby.posy) && image.getWidth()!=100) {
			image.translateTo(-100, -100);
			active = false;			
			points = points+1; 
			return points;
		}
		else{
			return 0;
		}	
	}

}