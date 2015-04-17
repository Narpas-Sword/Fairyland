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

public class DebugPanel extends JPanel
{
	private SpringLayout baseLayout;
	private JLabel radar;
	private ArrayList<JButton> objectList;

	public DebugPanel(FairylandController baseController)
	{
		setupPanel();
		setupLayout();
	}

	private void setupPanel()
	{
		baseLayout = new SpringLayout();
		radar = new JLabel(new ImageIcon((loadImg("src/narpas/fairyland/images/radar.png"))));
		this.add(radar);
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
