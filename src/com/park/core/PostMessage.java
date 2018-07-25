package com.park.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.park.beans.ServerError;

public final class PostMessage {
	public static void post(HttpServletResponse response,String msg)
	{
		try {
			response.getWriter().append(msg).flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void error(HttpServletResponse response,ServerError error)
	{
		PostMessage.post(response, JSON.toJSONString(error));
	}
}
