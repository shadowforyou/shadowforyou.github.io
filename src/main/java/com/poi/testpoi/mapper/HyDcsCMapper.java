package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDcsC;

@Mapper
public interface HyDcsCMapper {
	void add(HyDcsC hyDcsC);
	boolean delete(HyDcsC hyDcsC);
	Integer selectstcd(HyDcsC hyDcsC);
}
