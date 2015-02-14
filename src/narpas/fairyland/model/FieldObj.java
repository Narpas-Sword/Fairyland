package narpas.fairyland.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FieldObj
{

	private double posAngle;
	private double posRadius;
	private String type;
	private double turnAngle;
	private JLabel image;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	
	public FieldObj(double a)
	{
		posAngle = a;
		posRadius = 1;
		type = "tree";
		turnAngle = 0;
		image = new JLabel(new ImageIcon(barren_tree));
	}
	
	public FieldObj(double a, double r, String t)
	{
		posAngle = a;
		posRadius = r;
		type = t;
		turnAngle = 0;
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
	
	public JLabel getImage()
	{
		return image;
	}
	
	public void setPosAngle(double a)
	{
		posAngle = a;
	}
	
	public void setPosRadius(double r)
	{
		posRadius = r;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public void setTurnAngle(double ta)
	{
		turnAngle = ta;
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
