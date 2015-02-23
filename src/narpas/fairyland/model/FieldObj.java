package narpas.fairyland.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FieldObj implements Comparable
{

	/**	The angel of the object in relation to the player; 0 or 360 is in front, 90 is to the right, 180 is behind, and 270 is to the left	 */
	private double posAngle;
	/**	The distance away from the player an object is; 0 is at the player, and 1 is the farthest an object can be (when it is loaded and unloaded)	*/
	private double posRadius;
	/**	The type of object it is; current types are barren tree, apple tree, and monster	*/
	private String type;
	/**	The direction that an object is facing	*/
	private int turnAngle;
	/** The x position of the object	*/
	private double xPos;
	/** The y position of the object	*/
	private double yPos;
	/** The horizontal position of the on the panel for the label of this object	*/
	private double labelHorzPos;
	/** How large the lable of this object will be on the panel	*/
	private double labelSize;
	/** One degree in radians */
	private double angleValue = (Math.PI / 180);
	/**	The image an object uses, represented as a JLabel	*/
	private JLabel image;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	public BufferedImage blank = loadImg("src/narpas/fairyland/images/Blank_pixel.png");
	
	/**
	 * Loads a barren tree at angle "a" 
	 * @param a
	 */
	public FieldObj(double a)
	{
		posAngle = a;
		posRadius = 1;
		type = "tree";
		turnAngle = 0;
		image = new JLabel(new ImageIcon(barren_tree));
		xPos = Math.cos(posAngle * angleValue)*posRadius;
		yPos = Math.sin(posAngle * angleValue)*posRadius;
		updateLabel();
	}
	
	/**
	 * Loads an object with a radius of "r" and an angle of "a" of type "t"
	 * @param a
	 * @param r
	 * @param t
	 */
	public FieldObj(double a, double r, String t)
	{
		posAngle = a;
		posRadius = r;
		type = t;
		turnAngle = 0;
		xPos = Math.cos(posAngle * angleValue)*posRadius;
		yPos = Math.sin(posAngle * angleValue)*posRadius;
		updateLabel();
	}
	
	/**
	 * Sets the size and horizontal position for the label based on radius and the angle
	 */
	public void updateLabel()
	{
		//4*(2^(-x/1.5))-2
		//labelSize = 4*(Math.pow(2, -posRadius/1.5))-2;
		labelSize = -(posRadius)+2;
		image = resizeImage(new JLabel(new ImageIcon(barren_tree)), labelSize);
		if (posAngle <= 45 + image.getIcon().getIconWidth()/4 && xPos >= 0 && yPos >= 0)
			labelHorzPos = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/100)*(45 - posAngle) - image.getIcon().getIconWidth()/2;
		else if (posAngle >= 315 - image.getIcon().getIconWidth()/2 && xPos >= 0 && yPos < 0)
			labelHorzPos = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/100)*(90 - (posAngle - 315)) - image.getIcon().getIconWidth()/2;
		else
			labelHorzPos = -12345;
		
	}
	
	/**
	 * Resizes the inputed JLabel, by scaling by the size inputed	
	 * @param resizingImage
	 * @param size
	 * @return
	 */
	private JLabel resizeImage(JLabel resizingImage, double size)
	{
		if (size > 0)
			try
			{
				Image resizedImage = (Image) ((ImageIcon) resizingImage.getIcon()).getImage();
				resizedImage = resizedImage.getScaledInstance((int) (resizingImage.getIcon().getIconWidth()*size), (int) (resizingImage.getIcon().getIconHeight()*size), Image.SCALE_DEFAULT);
				return new JLabel(/*"X:" + xPos + " Y:" + yPos +" R:" + posRadius + " A:" + posAngle " L:" + labelHorzPos,*/ new ImageIcon(resizedImage), SwingConstants.CENTER);
			}
			catch (IllegalArgumentException e)
			{
				System.err.println("Caught IllegalArgumentException: " + e.getMessage());
				System.err.println("Returning null JLabel");
				return new JLabel(new ImageIcon(blank));
			}
		else
			return new JLabel(new ImageIcon(blank));
	}
	
	public double getPosAngle()
	{
		return posAngle;
	}
	
	public double getPosRadius()
	{
		return posRadius;
	}
	
	public String getType()
	{
		return type;
	}
	
	public double getTurnAngle()
	{
		return turnAngle;
	}
	
	public double getXPos()
	{
		return xPos;
	}
	
	public double getYPos()
	{
		return yPos;
	}
	
	public JLabel getImage()
	{
		return image;
	}
	
	public double getLabelHorzPos()
	{
		return labelHorzPos;
	}
	
	/**
	 * Sets the angle, and then sets the x and y position based on the change of the angle
	 * @param a
	 */
	public void setPosAngle(double a)
	{
		posAngle = a;
		xPos = Math.cos(posAngle * angleValue)*posRadius;
		yPos = Math.sin(posAngle * angleValue)*posRadius;
		updateLabel();
	}
	
	/**
	 * Sets the radius, and then sets the x and y position based on the change of the angle
	 * @param r
	 */
	public void setPosRadius(double r)
	{
		posRadius = r;
		xPos = Math.cos(posAngle * angleValue)*posRadius;
		yPos = Math.sin(posAngle * angleValue)*posRadius;
		updateLabel();
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public void setTurnAngle(int ta)
	{
		turnAngle = ta;
	}
	
	/**
	 * Sets the x position, and then sets the radius and angle based on the change of x
	 * @param x
	 */
	public void setXPos(double x)
	{
		xPos = x;
		posRadius = Math.sqrt(xPos*xPos + yPos*yPos);
		if (xPos > 0)
			posAngle = Math.atan(yPos/xPos)/angleValue;
		else if (xPos < 0)
			posAngle = Math.atan(yPos/xPos)/angleValue + 180;
		else if (yPos > 0)
			posAngle = 90;
		else if (yPos < 0)
			posAngle = 270;
		updateLabel();
	}
	
	/**
	 * Sets the y position, and then sets the radius and angle based on the change of y
	 * @param y
	 */
	public void setYPos(double y)
	{
		yPos = y;
		posRadius = Math.sqrt(xPos*xPos + yPos*yPos);
		if (xPos > 0)
			posAngle = Math.atan(yPos/xPos)/angleValue;
		else if (xPos < 0)
			posAngle = Math.atan(yPos/xPos)/angleValue + 180;
		else if (yPos > 0)
			posAngle = 90;
		else if (yPos < 0)
			posAngle = 270;
		updateLabel();
	}
	
	/**
	 * Compares this object to another object. 
	 * Returns 1 if the radius is larger or if the radius is equal and the angle is closer to zero
	 * Returns -1 if the radius is smaller or if the radius is equal and the angle is farther than zero. 
	 * Returns 0 if both the radius and the angle are equal
	 */
	public int compareTo(Object objObj)
	{
		int compareValue = 0;
		FieldObj otherField = (FieldObj) objObj;
		
		if (posRadius > otherField.getPosRadius())
			compareValue = 1;
		else if (posRadius < otherField.getPosRadius())
			compareValue = -1;
		else if (Math.abs(posAngle-180) < Math.abs(otherField.getPosRadius()-180))
			compareValue = 1;
		else if (Math.abs(posAngle-180) > Math.abs(otherField.getPosRadius()-180))
			compareValue = -1;
		
		return compareValue;
	}
	
	private static BufferedImage loadImg(String location)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(location));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return img;
	}
}
