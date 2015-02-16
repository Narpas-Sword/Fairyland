package narpas.fairyland.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import narpas.fairyland.view.FairylandPanel;

public class FairylandKeyListener implements KeyListener
{
	private FairylandPanel basePanel;
	
	public void setPanel(FairylandPanel panel)
	{
		basePanel = panel;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		basePanel.doKeyPress(KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		basePanel.doKeyRelease(KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
