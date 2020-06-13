package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDmxpF;

@Mapper
public interface HyDmxpFMapper {
	void add(HyDmxpF hyDmxpF);
	boolean delete(HyDmxpF hyDmxpF);
	Integer selectstcd(HyDmxpF hyDmxpF);
}
