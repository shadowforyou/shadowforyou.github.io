package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyYrqsF;
@Mapper
public interface HyYrqsFMapper {
	void add(HyYrqsF hyYrqsF);
	boolean delete(HyYrqsF hyYrqsF);
	Integer selectstcd(HyYrqsF hyYrqsF);
}
