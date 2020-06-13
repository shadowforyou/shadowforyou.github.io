package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyYrqF;
@Mapper
public interface HyYrqFMapper {
	void add(HyYrqF hyYrqF);
	boolean delete(HyYrqF hyYrqF);
	Integer selectstcd(HyYrqF hyYrqF);
}
