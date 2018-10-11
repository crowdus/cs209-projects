package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.AlphaComposite;
import javax.imageio.ImageIO;

import java.awt.Color;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	/* Position is a point where sprite is currently*/
	private Point position;

	static private Point zero = new Point(0,0);

	/* Pivot point is where the sprite turns from */
	private Point pivotPoint;

	/* Rotation is the number of degrees the sprite rotates*/
	private Integer rotation;

	/* Visible is a boolean to say if it is visible or not */
	private Boolean visible;

	/* Alpha is float to define transparency */
	private Float alpha;

	/* Alpha is the old alpha float value (previous frame) */
	private Float oldAlpha;

	/* scale x is a double to scale in x dir */
	private Double scaleX;

	/* scale y is a double to scale in y dir */
	private Double scaleY;

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setPivotPoint(Point pivotPoint) {
		this.pivotPoint = pivotPoint;
	}

	public Point getPivotPoint() {
		return this.pivotPoint;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public Integer getRotation() {
		return this.rotation;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setAlpha(Float alpha) {
		this.alpha = alpha;
	}

	public Float getAlpha() {
		return this.alpha;
	}

	public void setOldAlpha(Float oldAlpha) {
		this.oldAlpha = oldAlpha;
	}

	public Float getOldAlpha() {
		return oldAlpha;
	}

	public void setScaleX(Double scaleX) {
		this.scaleX = scaleX;
	}

	public Double getScaleX() {
		return scaleX;
	}

	public void setScaleY(Double scaleY) {
		this.scaleY = scaleY;
	}

	public Double getScaleY() {
		return scaleY;
	}

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */

	public DisplayObject(String id) {
		this.position = zero;
		this.pivotPoint = zero;
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		this.position = zero;
		this.pivotPoint = zero;
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.setId(id);
		this.setImage(fileName);
	}

	public DisplayObject(String id, String fileName, Point position) {
		this.position = position;
		this.pivotPoint = zero;
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.setId(id);
		this.setImage(fileName);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0 ,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(this.position.x, this.position.y);
	//	g2d.translate(this.pivotPoint.x, this.pivotPoint.y);
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
				this.alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(3,
				this.oldAlpha));
		g2d.scale(1 / this.scaleX, 1 / this.scaleY);
		g2d.rotate(Math.toRadians(- this.getRotation()));
	//	g2d.translate(this.pivotPoint.x, this.pivotPoint.y );
		g2d.rotate(Math.toRadians(- this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		g2d.translate(-this.position.x, -this.position.y);
	}

}
