package narpas.fairyland.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FairylandKeyListener implements KeyListener
{

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
