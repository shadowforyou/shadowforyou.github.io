package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMtqsE;
@Mapper
public interface HyMtqsEMapper {
	void add(HyMtqsE hyMtqsE);
	boolean delete(HyMtqsE hyMtqsE);
	Integer selectstcd(HyMtqsE hyMtqsE);
}
