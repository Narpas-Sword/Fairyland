package narpas.fairyland.view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import narpas.fairyland.controller.FairylandController;
import narpas.fairyland.picture.DigitalPicture;
import narpas.fairyland.picture.ImageDisplay;
import narpas.fairyland.picture.Picture;
import javax.swing.JLabel;

public class FairylandPanel extends JPanel
{
	private ArrayList fieldObjList;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	/** The image icon used to display the picture */
	private ImageIcon scrollImageIcon;
	/** The image display */
	private ImageDisplay imageDisplay;
	private FairylandController baseController;
	private SpringLayout baseLayout;

	public FairylandPanel(FairylandController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		//this.add(whatever);
	}
	
	private void setupLayout()
	{
		//baseLayout.putConstraints...
	}
	
	private void setupListeners()
	{
		
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
