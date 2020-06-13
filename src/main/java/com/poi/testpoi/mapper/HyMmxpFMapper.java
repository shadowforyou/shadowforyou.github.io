package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMmxpF;
@Mapper
public interface HyMmxpFMapper {
	void add(HyMmxpF hyMmxpF);
	boolean delete(HyMmxpF hyMmxpF);
	Integer selectstcd(HyMmxpF hyMmxpF);
	
}
