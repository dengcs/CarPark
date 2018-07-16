package com.park.core;

public final class Dispatcher {
	private static Dispatcher instance = null;
	
	private Dispatcher()
	{
		
	}
	
	public synchronized static Dispatcher getInstance()
	{
		if(instance == null)
		{
			instance = new Dispatcher();
		}
		
		return instance;
	}
	
	public boolean login_dispatch(String msg)
	{
		if(Protocal.getInstance().checkLoginValid(""))
		{
			return true;
		}
		return false;
	}
	
	public boolean request_dispatch(String msg)
	{
		if(Protocal.getInstance().checkLoginValid(""))
		{
			return true;
		}
		return false;
	}
}
