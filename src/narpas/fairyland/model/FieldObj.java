package narpas.fairyland.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FieldObj
{

	/**	The angel of the object in relation to the player; 0 or 360 is in front, 90 is to the right, 180 is behind, and 270 is to the left	 */
	private double posAngle;
	/**	The distance away from the player an object is; 0 is at the player, and 1 is the farthest an object can be (when it is loaded and unloaded)	*/
	private double posRadius;
	/**	The type of object it is; current types are barren tree, apple tree, and monster	*/
	private String type;
	/**	The direction that an object is facing	*/
	private double turnAngle;
	private double xPos;
	private double yPos;
	private double labelHorzPos;
	private double labelSize;
	/**	The image an object uses, represented as a JLabel	*/
	private JLabel image;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	
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
		xPos = Math.cos(posAngle)*posRadius;
		yPos = Math.sin(posAngle)*posRadius;
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
		xPos = Math.cos(posAngle)*posRadius;
		yPos = Math.sin(posAngle)*posRadius;
		updateLabel();
	}
	
	public void updateLabel()
	{
		labelSize = -0.5*posRadius + 4;
		image = resizeImage(new JLabel(new ImageIcon(barren_tree)), labelSize);
		if (posAngle <= 50 + image.getWidth())
			labelHorzPos = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/100)*(50 - posAngle) - image.getWidth()/2;
		else if (posAngle >= 310 -image.getWidth())
			labelHorzPos = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/100)*(100 - (posAngle - 310)) - image.getWidth()/2;
		else
			labelHorzPos = -123.45;
		
	}
	
	/**
	 * Resizes the inputed JLabel, by scaling by the size inputed	
	 * @param resizingImage
	 * @param size
	 * @return
	 */
	private JLabel resizeImage(JLabel resizingImage, double size)
	{
		try
		{
			Image resizedImage = (Image) ((ImageIcon) resizingImage.getIcon()).getImage();
			resizedImage = resizedImage.getScaledInstance((int) (resizingImage.getIcon().getIconWidth()*size), (int) (resizingImage.getIcon().getIconHeight()*size), Image.SCALE_DEFAULT);
			return new JLabel(new ImageIcon(resizedImage));
		}
		catch (IllegalArgumentException e)
		{
			System.err.println("Caught IllegalArgumentException: " + e.getMessage());
			System.err.println("Returning inputed JLabel");
			return resizingImage;
		}
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
	
	public void setPosAngle(double a)
	{
		posAngle = a;
		xPos = Math.cos(posAngle)*posRadius;
		yPos = Math.sin(posAngle)*posRadius;
		updateLabel();
	}
	
	public void setPosRadius(double r)
	{
		posRadius = r;
		xPos = Math.cos(posAngle)*posRadius;
		yPos = Math.sin(posAngle)*posRadius;
		updateLabel();
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public void setTurnAngle(double ta)
	{
		turnAngle = ta;
	}
	
	public void setXPos(double x)
	{
		xPos = x;
		posRadius = Math.sqrt(xPos*xPos + yPos*yPos);
		posAngle = Math.atan(yPos/xPos);
		updateLabel();
	}
	
	public void setYPos(double y)
	{
		yPos = y;
		posRadius = Math.sqrt(xPos*xPos + yPos*yPos);
		posAngle = Math.atan(yPos/xPos);
		updateLabel();
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
