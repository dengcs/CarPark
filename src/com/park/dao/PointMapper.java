package com.park.dao;

import java.util.List;

import com.park.beans.Point;

public interface PointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Point record);

    int insertSelective(Point record);

    Point selectByPrimaryKey(Integer id);
    
    List<Point> selectListByPoint(Point record);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
}