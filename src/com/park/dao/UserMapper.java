package com.park.dao;

import com.park.beans.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByUser(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}