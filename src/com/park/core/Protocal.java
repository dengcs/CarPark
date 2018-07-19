package com.park.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
		if(loginSet.isEmpty()==false && loginSet.isEmpty()==false)
		{
			return;
		}
		
		String pathname = MyResourcesPath.getProtocalPath();
		File myFile = new File(pathname);
		InputStream is;
		try {
			is = new FileInputStream(myFile);
			String jsonStr = IOUtils.toString(is, StandardCharsets.UTF_8);
			
			JSONObject jsonObj = JSONObject.parseObject(jsonStr);
			JSONArray loginArray =  jsonObj.getJSONArray("login");
			JSONArray reqArray =  jsonObj.getJSONArray("request");
			
			if(loginArray != null)
			{
				loginSet.clear();
				for (Object protoName : loginArray) {
					loginSet.add((String)protoName);
				}
			}
			
			if(reqArray != null)
			{
				RequestSet.clear();
				for (Object protoName : reqArray) {
					RequestSet.add((String)protoName);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean checkLoginValid(String protoName)
	{
		fetchProtoSet();
		
		if(loginSet.contains(protoName))
		{
			return true;
		}
		
		return false;
	}
	
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
