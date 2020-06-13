package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyFdheexB;

@Mapper
public interface HyFdheexBMapper {
	void add(HyFdheexB hyFdheexB);
	boolean delete(HyFdheexB hyFdheexB);
	Integer selectstcd(HyFdheexB hyFdheexB);
}
