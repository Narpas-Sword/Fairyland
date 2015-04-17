package narpas.fairyland.view;

import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import narpas.fairyland.controller.FairylandController;

public class DebugFrame extends JFrame
{
	private DebugPanel basePanel;

	public DebugFrame(FairylandController baseController)
	{
		setResizable(false);
		basePanel = new DebugPanel(baseController);
		setupFrame();
	}

	/**
	 * Sets the frame up
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(420, 440);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public DebugPanel getPanel()
	{
		return basePanel;
	}
}
