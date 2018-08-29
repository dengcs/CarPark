package com.park.test;

import org.apache.ibatis.session.SqlSession;

import com.park.beans.User;
import com.park.core.DBTools;
import com.park.dao.UserMapper;

public class MainTest {

	public static void main(String[] args) {
		User user = new User();
		user.setAccount("dengcs");
		user.setPassword("123456");
		user.setName("小邓");
		user.setSex(1);
		
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
            mapper.insertSelective(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
	}

}
