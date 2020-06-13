package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyYrweF;
@Mapper
public interface HyYrweFMapper {
	void add(HyYrweF hyYrweF);
	boolean delete(HyYrweF hyYrweF);
	Integer selectstcd(HyYrweF hyYrweF);
}
