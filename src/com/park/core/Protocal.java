package com.park.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import main.resources.MyResourcesPath;

public final class Protocal {
	private static Protocal instance = null;
	
	private Set<String> loginSet = new HashSet<String>();
	private Set<String> RequestSet = new HashSet<String>();
	
	private Protocal()
	{
		
	}
	
	public synchronized static Protocal getInstance()
	{
		if(instance == null)
		{
			instance = new Protocal();
		}
		
		return instance;
	}
	
	private void fetchProtoSet()
	{
		String pathname = MyResourcesPath.getProtocalPath();
		File myFile = new File(pathname);
		InputStream is;
		try {
			is = new FileInputStream(myFile);
			String jsonStr = IOUtils.toString(is, StandardCharsets.UTF_8);
			
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//检查登录协议是否有效
	public boolean checkLoginValid(String protoName)
	{
		fetchProtoSet();
		
		if(loginSet.contains(protoName))
		{
			return true;
		}
		
		return false;
	}
	
	//检查请求协议是否有效
	public boolean checkRequestValid(String protoName)
	{
		fetchProtoSet();
		
		if(RequestSet.contains(protoName))
		{
			return true;
		}
		
		return false;
	}
}
