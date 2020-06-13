package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDqsC;
@Mapper
public interface HyDqsCMapper {
	void add(HyDqsC hyDqsC);
	boolean delete(HyDqsC hyDqsC);
	Integer selectstcd(HyDqsC hyDqsC);
}
