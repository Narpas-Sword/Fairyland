package narpas.fairyland.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import narpas.fairyland.controller.FairylandController;
import narpas.fairyland.model.FairylandKeyListener;
import narpas.fairyland.model.FieldObj;
import javax.swing.JLabel;

public class FairylandPanel extends JPanel
{
	/**	The list that carries the objects	*/
	private ArrayList fieldObjList;
	private ArrayList view;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	private FairylandController baseController;
	private SpringLayout baseLayout;
	/**	Listens for when keys are pressed by the player	*/
	private FairylandKeyListener baseKeyListener;
	private String keyPress;
	
		//Debug Stuff
		private JLabel testLabel;
		private double resizeSize;
		private int xScreenPos;
		//Debug Stuff
	
	public FairylandPanel(FairylandController baseController)
	{
		resizeSize = 1.0;
		xScreenPos = 0;
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		fieldObjList = new ArrayList(0);
		view = new ArrayList(0);
		fieldObjList.add(new FieldObj(0));
		baseKeyListener = new FairylandKeyListener();
		view.add(((FieldObj) fieldObjList.get(0)).getImage());
		//this.add(((Component) view.get(0)), this.getComponentCount());
		testLabel = (JLabel) view.get(0);
		setupPanel();
		setupLayout();
		setupListeners();		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		add(testLabel);
		//this.add(whatever);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, testLabel, 0, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, testLabel, 0, SpringLayout.WEST, this);
		//baseLayout.putConstraints...
	}
	
	private void setupListeners()
	{
		this.addKeyListener(baseKeyListener);
		baseKeyListener.setPanel(this);
		this.setFocusable(true);
	}
	
	/**
	 * Method for what happens when a key is pressed;
	 * Called by FairylandKeyListener
	 * @param inputKeyPressed
	 */
	public void doKeyPress(String inputKeyPressed)
	{
		if (inputKeyPressed == "Up")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				fieldObj.setXPos(fieldObj.getXPos()-0.1);
			}
			resizeSize += .01;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			testLabel.setIcon(this.resizeImage(new JLabel(new ImageIcon(barren_tree)), resizeSize).getIcon());
			this.add(testLabel);
			this.revalidate();
			this.repaint();
		}
		if (inputKeyPressed == "Down")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				fieldObj.setXPos(fieldObj.getXPos()+0.1);
			}
			resizeSize -= .01;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			testLabel.setIcon(this.resizeImage(new JLabel(new ImageIcon(barren_tree)), resizeSize).getIcon());
			this.add(testLabel);
			this.revalidate();
			this.repaint();
		}
		if (inputKeyPressed == "Left")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				if (fieldObj.getPosAngle() >= 0.1)
					fieldObj.setPosAngle(fieldObj.getPosAngle()-0.1);
				else
					fieldObj.setPosAngle(360-(fieldObj.getPosAngle()-0.1));
			}
			xScreenPos -= 1;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			baseLayout.putConstraint(SpringLayout.WEST, testLabel, xScreenPos, SpringLayout.WEST, this);
			this.add(testLabel);
			this.revalidate();
			this.repaint();
		}
		if (inputKeyPressed == "Right")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				if (fieldObj.getPosAngle() < 359.9)
					fieldObj.setPosAngle(fieldObj.getPosAngle()+0.1);
				else
					fieldObj.setPosAngle((fieldObj.getPosAngle()+0.1)-360);
			}
			xScreenPos += 1;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			baseLayout.putConstraint(SpringLayout.WEST, testLabel, xScreenPos, SpringLayout.WEST, this);
			this.add(testLabel);
			this.revalidate();
			this.repaint();
		}
	}
	
	public void doKeyRelease(String inputKeyReleased)
	{
		
	}
	
	/**
	 * Loads the image at the location "location"
	 * @param location
	 * @return
	 */
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
}
