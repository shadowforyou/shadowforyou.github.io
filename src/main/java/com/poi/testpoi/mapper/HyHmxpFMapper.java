package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyHmxpF;

@Mapper
public interface HyHmxpFMapper {
	void add(HyHmxpF hyHmxpF);
	boolean delete(HyHmxpF hyHmxpF);
	Integer selectstcd(HyHmxpF hyHmxpF);
	
}
