package narpas.fairyland.view;

import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import narpas.fairyland.controller.FairylandController;

public class DebugFrame extends JFrame
{
	private DebugPanel debug;

	public DebugFrame(FairylandController baseController, FairylandPanel basePanel)
	{
		setResizable(false);
		debug = new DebugPanel(baseController, basePanel);
		setupFrame();
	}

	/**
	 * Sets the frame up
	 */
	private void setupFrame()
	{
		this.setContentPane(debug);
		this.setSize(420, 440);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public DebugPanel getPanel()
	{
		return debug;
	}
}
