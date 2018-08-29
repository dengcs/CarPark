package com.park.service;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.park.beans.ServerError;
import com.park.core.PostMessage;

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
					} catch (Exception e) {
						ServerError error = new ServerError();
						error.setError(-3);
						error.setDescribe(data);
						PostMessage.error(response, error);
						e.printStackTrace();
					}
				}
			}
		}
	}
}
