package narpas.fairyland.controller;

import narpas.fairyland.view.DebugFrame;
import narpas.fairyland.view.FairylandFrame;

public class FairylandController
{
	private FairylandFrame baseFrame;
	private DebugFrame debug;
	
	public FairylandController()
	{
		baseFrame = new FairylandFrame(this);
		debug = new DebugFrame(this);
		baseFrame.getPanel().recreateView();
	}

	public void start()
	{
		
	}
	
	public FairylandFrame getFrame()
	{
		return baseFrame;
	}
	
	public DebugFrame getDebug()
	{
		return debug;
	}
}
