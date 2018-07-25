package com.park.service;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.park.beans.ServerError;
import com.park.beans.User;
import com.park.core.DBTools;
import com.park.core.PostMessage;
import com.park.dao.UserMapper;

public class UserService extends BaseService{
	
	public UserService()
	{
		this.register();
	}
	
	@Override
	public void register() {
		this.setProtocol(this);
	}

	@Override
	public boolean messageHandle(HttpServletResponse response, String protoName, String data) {
		if(this.hasProtocol(protoName))
		{
			this.invokeProtocol(this, response, protoName, data);
			return true;
		}		
		return false;
	}
	
	@ProtocolMethod
	public void user_register(HttpServletResponse response, String jsonData)
	{
		User user = JSON.parseObject(jsonData, User.class);
		if(user != null)
		{
			SqlSession session = DBTools.getSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			try {
	            mapper.insertSelective(user);
	            session.commit();
	            
	            ServerError error = new ServerError();
				error.setError(0);
				PostMessage.error(response, error);
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.rollback();
	            
	            ServerError error = new ServerError();
				error.setError(0);
				error.setDescribe(e.getMessage());
				PostMessage.error(response, error);
	        }
		}else
		{
			ServerError error = new ServerError();
			error.setError(-3);
			error.setDescribe("data is null");
			PostMessage.error(response, error);
		}
		
	}
}
