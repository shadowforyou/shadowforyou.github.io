package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyYrcsF;

@Mapper
public interface HyYrcsFMapper {
	void add(HyYrcsF hyYrcsF);
	boolean delete(HyYrcsF hyYrcsF);
	Integer selectstcd(HyYrcsF hyYrcsF);
}
