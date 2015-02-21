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
	private ArrayList<FieldObj> fieldObjList;
	private ArrayList<JLabel> view;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	private FairylandController baseController;
	private SpringLayout baseLayout;
	/**	Listens for when keys are pressed by the player	*/
	private FairylandKeyListener baseKeyListener;
	
		//Debug Stuff
//		private JLabel testLabel;
//		private double resizeSize;
//		private int xScreenPos;
//		private JLabel testLabelDisplay;
		//Debug Stuff
	
	public FairylandPanel(FairylandController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		fieldObjList = new ArrayList<FieldObj>(0);
		view = new ArrayList<JLabel>(0);
		fieldObjList.add(new FieldObj(0, 0.5, "tree"));
		fieldObjList.add(new FieldObj(90, 0.5, "tree"));
		fieldObjList.add(new FieldObj(180, 0.5, "tree"));
		fieldObjList.add(new FieldObj(270, 0.5, "tree"));
		baseKeyListener = new FairylandKeyListener();
		view.add(((FieldObj) fieldObjList.get(0)).getImage());
		setupPanel();
		setupLayout();
		setupListeners();		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		/*	Debug Stuff	*	/
		add(testLabel);
		add(testLabelDisplay);
		/	*	Debug Stuff	*/
	}
	
	private void setupLayout()
	{
		/*	Debug Stuff	*	/
		baseLayout.putConstraint(SpringLayout.NORTH, testLabel, 0, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, testLabel, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, testLabelDisplay, 0, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, testLabelDisplay, 0, SpringLayout.WEST, this);
		/	*	Debug Stuff	*/
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
				fieldObj.setXPos(fieldObj.getXPos()-0.01);
				if (fieldObj.getPosAngle() >= 360)
					fieldObj.setPosAngle(fieldObj.getPosAngle()-360);
				if (fieldObj.getPosAngle() < 0)
					fieldObj.setPosAngle(fieldObj.getPosAngle()+360);
			}
			recreateView();
			
			/*	Debug Stuff	*	/
			resizeSize += .01;
			this.remove(testLabel);
			this.remove(testLabelDisplay);
			this.revalidate();
			this.repaint();
			testLabel.setIcon(this.resizeImage(new JLabel(new ImageIcon(barren_tree)), resizeSize).getIcon());
			testLabelDisplay = new JLabel(resizeSize + "");
			this.add(testLabel);
			this.add(testLabelDisplay);
			this.revalidate();
			this.repaint();
			/	*	Debug Stuff	*/
		}
		if (inputKeyPressed == "Down")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				fieldObj.setXPos(fieldObj.getXPos()+0.01);
				if (fieldObj.getPosAngle() >= 360)
					fieldObj.setPosAngle(fieldObj.getPosAngle()-360);
				if (fieldObj.getPosAngle() < 0)
					fieldObj.setPosAngle(fieldObj.getPosAngle()+360);
			}
			recreateView();
			
			/*	Debug Stuff	*	/
			resizeSize -= .01;
			this.remove(testLabel);
			this.remove(testLabelDisplay);
			this.revalidate();
			this.repaint();
			testLabel.setIcon(this.resizeImage(new JLabel(new ImageIcon(barren_tree)), resizeSize).getIcon());
			testLabelDisplay = new JLabel(resizeSize + "");
			this.add(testLabel);
			this.add(testLabelDisplay);
			this.revalidate();
			this.repaint();
			/	*	Debug Stuff	*/
		}
		if (inputKeyPressed == "Left")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				fieldObj.setPosAngle(fieldObj.getPosAngle()-5);
				if (fieldObj.getPosAngle() < 0)
					fieldObj.setPosAngle(fieldObj.getPosAngle()+360);
				if (fieldObj.getPosAngle() >= 360)
					fieldObj.setPosAngle(fieldObj.getPosAngle()-360);
			}
			recreateView();
			
			/*	Debug Stuff	*	/
			xScreenPos -= 1;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			baseLayout.putConstraint(SpringLayout.WEST, testLabel, xScreenPos, SpringLayout.WEST, this);
			this.add(testLabel);
			this.revalidate();
			this.repaint();
			/	*	Debug Stuff	*/
		}
		if (inputKeyPressed == "Right")
		{
			for (Object objObj : fieldObjList)
			{
				FieldObj fieldObj = (FieldObj) objObj;
				fieldObj.setPosAngle(fieldObj.getPosAngle()+5);
				if (fieldObj.getPosAngle() >= 360)
					fieldObj.setPosAngle(fieldObj.getPosAngle()-360);
				if (fieldObj.getPosAngle() < 0)
					fieldObj.setPosAngle(fieldObj.getPosAngle()+360);
			}
			recreateView();
			
			/*	Debug Stuff	*	/
			xScreenPos += 1;
			this.remove(testLabel);
			this.revalidate();
			this.repaint();
			baseLayout.putConstraint(SpringLayout.WEST, testLabel, xScreenPos, SpringLayout.WEST, this);
			this.add(testLabel);
			this.revalidate();
			this.repaint();
			/	*	Debug Stuff	*/
		}
	}
	
	public void recreateView()
	{
		this.removeAll();
		view.clear();
		for (Object objObj : fieldObjList)
		{
			FieldObj fieldObj = (FieldObj) objObj;
			if (fieldObj.getLabelHorzPos() != -12345 && fieldObj.getXPos() > 0 && fieldObj.getPosRadius() < 1.1 && (fieldObj.getPosAngle() < 50 || fieldObj.getPosAngle() > 310))
			{
				view.add(fieldObj.getImage());
				baseLayout.putConstraint(SpringLayout.WEST, (Component) view.get(view.size()-1), (int) fieldObj.getLabelHorzPos(), SpringLayout.WEST, this);
				baseLayout.putConstraint(SpringLayout.SOUTH, (Component) view.get(view.size()-1), (int) (-(this.getHeight()/1.75) + ((JLabel) view.get(view.size()-1)).getIcon().getIconHeight()/2), SpringLayout.SOUTH, this);
				this.add((Component) view.get(view.size()-1));
			}
		}
		//testLabelDisplay = new JLabel(("Angle: " + ((FieldObj) fieldObjList.get(fieldObjList.size()-1)).getPosAngle()) + ", Radius: " + ((FieldObj) fieldObjList.get(fieldObjList.size()-1)).getPosRadius() + ", X: " + ((FieldObj) fieldObjList.get(fieldObjList.size()-1)).getXPos() + ", Y: " + ((FieldObj) fieldObjList.get(fieldObjList.size()-1)).getYPos() + ", Label Position: " + ((FieldObj) fieldObjList.get(fieldObjList.size()-1)).getLabelHorzPos());
		//this.add(testLabelDisplay);
		revalidate();
		repaint();
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
			System.err.println("Returning null JLabel");
			return new JLabel(new ImageIcon());
		}
	}
}
