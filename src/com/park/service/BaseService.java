package com.park.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseService {
	
	private Set<String> protoSet = new HashSet<String>();
	
	public abstract void register();
	public abstract boolean messageHandle(HttpServletResponse response, String protoName, String data);
	
	public void setProtocol(BaseService service)
	{
		Class<? extends BaseService> c = service.getClass();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			ProtocolMethod pm = method.getAnnotation(ProtocolMethod.class);
			if(pm != null)
			{
				protoSet.add(method.getName());
			}
		}
	}
	
	public boolean hasProtocol(String protoName)
	{
		return protoSet.contains(protoName);
	}
	
	// 处理协议
	public void invokeProtocol(BaseService service, HttpServletResponse response, String protoName, String data)
	{
		Class<? extends BaseService> c = service.getClass();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			ProtocolMethod pm = method.getAnnotation(ProtocolMethod.class);
			if (pm != null)
			{
				String methodName = method.getName();
				if(methodName.equalsIgnoreCase(protoName))
				{
					method.setAccessible(true);
					try {
						method.invoke(service, response, data);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
