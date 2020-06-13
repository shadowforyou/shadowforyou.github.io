package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyYrzF;
@Mapper
public interface HyYrzFMapper {
	void add(HyYrzF hyYrzF);
	boolean delete(HyYrzF hyYrzF);
	Integer selectstcd(HyYrzF hyYrzF);
}
