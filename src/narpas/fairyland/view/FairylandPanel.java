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
	private ArrayList fieldObjList;
	private ArrayList view;
	private ArrayList keyPressesArray;
	public BufferedImage barren_tree = loadImg("src/narpas/fairyland/images/barren_tree2.png");
	private FairylandController baseController;
	private SpringLayout baseLayout;
	private FairylandKeyListener baseKeyListener;
	private String keyPress;
	private int index;

	public FairylandPanel(FairylandController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		fieldObjList = new ArrayList(0);
		view = new ArrayList(0);
		keyPressesArray = new ArrayList(0);
		fieldObjList.add(new FieldObj(0));
		baseKeyListener = new FairylandKeyListener();
		view.add(((FieldObj) fieldObjList.get(0)).getImage());
		this.add(((Component) view.get(0)), this.getComponentCount());
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
		this.addKeyListener(baseKeyListener);
		baseKeyListener.setPanel(this);
		this.setFocusable(true);
	}
	
	public void doKeyPress(String inputKeyPressed)
	{
		if (inputKeyPressed == "Up")
		{
				this.remove(this.getComponentCount()-1);
				view.set(0, resizeImage((JLabel) view.get(0), 1.1));
				this.add(((Component) view.get(0)), this.getComponentCount());
		}
	}
	
	public void doKeyRelease(String inputKeyReleased)
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
	
	private JLabel resizeImage(JLabel resizingImage, double size)
	{
		Image resizedImage = (Image) ((ImageIcon) resizingImage.getIcon()).getImage();
		resizedImage.getScaledInstance(resizingImage.getIcon().getIconWidth(), resizingImage.getIcon().getIconHeight(), Image.SCALE_DEFAULT);
		return new JLabel(new ImageIcon(resizedImage));
	}
}
