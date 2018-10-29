package edu.virginia.lab1test;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import java.awt.Point;
import java.util.concurrent.TimeUnit;
import java.awt.Graphics2D;
import edu.virginia.engine.display.Animation;
import edu.virginia.engine.display.AnimatedSprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game{

	Point pt = new Point(300,300);
	/* Create a sprite object for our game. We'll use mario */
	Sprite sun = new Sprite("Sun", "Sun.png", pt);


	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab Three Test Game", 900, 900);
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);

		/* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if (sun != null) sun.update(pressedKeys);

/*		if (pressedKeys.contains(KeyEvent.VK_UP)) {
			mario.setPosition(new Point(mario.getPosition().x,
					mario.getPosition().y - 5));
		}
		if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
			mario.setPosition(new Point(mario.getPosition().x,
					mario.getPosition().y + 5));
		}
		if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			mario.setPosition(new Point(mario.getPosition().x + 5,
					mario.getPosition().y));
		}
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			mario.setPosition(new Point(mario.getPosition().x - 5,
					mario.getPosition().y));
		}
		if (pressedKeys.contains(KeyEvent.VK_I)) {
			mario.setPivotPoint(new Point(mario.getPivotPoint().x,
					mario.getPivotPoint().y - 5));
		}
		if (pressedKeys.contains(KeyEvent.VK_K)) {
			mario.setPivotPoint(new Point(mario.getPivotPoint().x,
					mario.getPivotPoint().y + 5));
		}
		if (pressedKeys.contains(KeyEvent.VK_J)) {
			mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5,
					mario.getPivotPoint().y));
		}
		if (pressedKeys.contains(KeyEvent.VK_L)) {
			mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5,
					mario.getPivotPoint().y));
		}
		if (pressedKeys.contains(KeyEvent.VK_W)) {
			mario.setRotation(new Integer(mario.getRotation() + 10));
		}
		if (pressedKeys.contains(KeyEvent.VK_Q)) {
			mario.setRotation(new Integer(mario.getRotation() - 10));
		}
		if (pressedKeys.contains(KeyEvent.VK_V)) {
			if (mario.getVisible()) {
				mario.setVisible(false);
				mario.setOldAlpha(mario.getAlpha());
				mario.setAlpha(0.0f);
				//found the code for "sleeping" on stackoverflow. Jean mentioned on Piazza to use a timer so we needed
				 // to look up how to implement one
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				mario.setVisible(true);
				mario.setAlpha(mario.getOldAlpha());
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (mario.getAlpha() <= 0.0f) {
			mario.setAlpha(0.0f);
		}
		if (mario.getAlpha() > 0.01f) {
			if (pressedKeys.contains(KeyEvent.VK_Z)) {
				mario.setAlpha(new Float(mario.getAlpha() - .01f));
			}
		}
		if (mario.getAlpha() < 1.0f) {
			if (pressedKeys.contains(KeyEvent.VK_X)) {
				mario.setAlpha(new Float(mario.getAlpha() + .01f));
			}
		}
		if (pressedKeys.contains(KeyEvent.VK_A)) {
			mario.setScaleX(new Double(mario.getScaleX() * 1.1));
			mario.setScaleY(new Double(mario.getScaleY() * 1.1));
		}
		if (pressedKeys.contains(KeyEvent.VK_S)) {
			mario.setScaleX(new Double(mario.getScaleX() * .9));
			mario.setScaleY(new Double(mario.getScaleY() * .9));
		}
*/
/*		if (pressedKeys.contains(KeyEvent.VK_1)) { //decrement speed
			mario.setAnimationSpeed(mario.getAnimationSpeed()-100);
			System.out.println("speed " + mario.getAnimationSpeed());
		}
		if (pressedKeys.contains(KeyEvent.VK_2)) { //increment speed
			mario.setAnimationSpeed(mario.getAnimationSpeed()+100);
		}
		if (pressedKeys.contains(KeyEvent.VK_3)) { //decrement speed
			mario.setCurrentFrame(0);
			mario.setStartFrame(0);
			mario.setEndFrame(1);
			mario.animate("jump");
		}
		if (pressedKeys.contains(KeyEvent.VK_4)) { //decrement speed
			mario.setCurrentFrame(2);
			mario.setStartFrame(2);
			mario.setEndFrame(3);
			mario.animate("kick");
		}
	*/
	}

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(sun != null) sun.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();

	}
}