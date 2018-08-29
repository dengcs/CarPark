package com.park.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.park.service.BaseService;
import com.park.service.PointService;
import com.park.service.UserService;

public final class Dispatcher {
	private static Dispatcher instance = null;
	
	private List<BaseService> loginServiceList = new ArrayList<BaseService>();
	private List<BaseService> requestServiceList = new ArrayList<BaseService>();
	
	private Dispatcher()
	{
		loginServiceList.add(new UserService());
		requestServiceList.add(new PointService());
	}
	
	public synchronized static Dispatcher getInstance()
	{
		if(instance == null)
		{
			instance = new Dispatcher();
		}
		
		return instance;
	}
	
	public boolean login_dispatch(HttpServletResponse response, String protoName, String data)
	{
		for(BaseService service:loginServiceList)
		{
			if(service.messageHandle(response, protoName, data))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean request_dispatch(HttpServletResponse response, String protoName, String data)
	{
		for(BaseService service:requestServiceList)
		{
			if(service.messageHandle(response, protoName, data))
			{
				return true;
			}
		}
		return false;
	}
}
