package narpas.fairyland.view;

import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import narpas.fairyland.controller.FairylandController;

public class FairylandFrame extends JFrame
{
	private FairylandPanel basePanel;

	public FairylandFrame(FairylandController baseController)
	{
		setResizable(false);
		basePanel = new FairylandPanel(baseController);
		setupFrame();
	}

	/**
	 * Sets the frame up
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setResizable(false);
		this.setVisible(true);
	}
}
