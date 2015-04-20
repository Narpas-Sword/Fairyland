package narpas.fairyland.view;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import narpas.fairyland.controller.FairylandController;
import narpas.fairyland.model.FieldObj;

public class DebugPanel extends JPanel
{
	private SpringLayout baseLayout;
	private JLabel radar;
	private ArrayList<JButton> objectList;
	private FairylandPanel basePanel;

	public DebugPanel(FairylandController baseController, FairylandPanel basePanel)
	{
		this.basePanel = basePanel;
		baseLayout = new SpringLayout();
		setupPanel();
		setupLayout();
	}

	private void setupPanel()
	{
		radar = new JLabel(new ImageIcon((loadImg("src/narpas/fairyland/images/radar.png"))));
		this.add(radar);
	}
	
	public void update(ArrayList<FieldObj> objList)
	{
		this.removeAll();
		setupPanel();
		setupLayout();
		for (FieldObj object : objList)
		{
			JLabel objectLabel = new JLabel(new ImageIcon((loadImg("src/narpas/fairyland/images/stump.png"))));
			this.add(objectLabel);
			baseLayout.putConstraint(SpringLayout.WEST, objectLabel, 200 + (int) (200 * object.getYPos()), SpringLayout.WEST, radar);
			baseLayout.putConstraint(SpringLayout.NORTH, objectLabel, 200 + (int) (200 * object.getXPos()), SpringLayout.NORTH, radar);
		}
		revalidate();
		repaint();
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, (Component) radar, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, (Component) radar, 0, SpringLayout.NORTH, this);
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
