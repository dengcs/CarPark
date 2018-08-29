package com.park.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.park.beans.Point;
import com.park.beans.ServerError;
import com.park.core.DBTools;
import com.park.core.PostMessage;
import com.park.dao.PointMapper;

public class PointService extends BaseService {

	public PointService()
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
	public void point_set(HttpServletResponse response, String jsonData)
	{
		Point point = JSON.parseObject(jsonData, Point.class);
		if(point != null)
		{
			SqlSession session = DBTools.getSession();
			PointMapper mapper = session.getMapper(PointMapper.class);
			mapper.insert(point);
			
			ServerError error = new ServerError();
			error.setError(0);
			PostMessage.error(response, error);
		}
	}
	
	@ProtocolMethod
	public void point_get(HttpServletResponse response, String jsonData)
	{
		Point point = JSON.parseObject(jsonData, Point.class);
		if(point != null)
		{
			SqlSession session = DBTools.getSession();
			PointMapper mapper = session.getMapper(PointMapper.class);
			List<Point> pointList = mapper.selectListByPoint(point);
			
			PostMessage.post(response, JSONArray.toJSONString(pointList));
		}
	}
}
