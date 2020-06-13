package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMtzE;
@Mapper
public interface HyMtzEMapper {
	void add(HyMtzE hyMtzE);
	boolean delete(HyMtzE hyMtzE);
	Integer selectstcd(HyMtzE hyMtzE);
}
