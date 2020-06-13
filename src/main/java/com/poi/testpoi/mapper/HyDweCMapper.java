package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDweC;

@Mapper
public interface HyDweCMapper {
	void add(HyDweC hyDweC);
	boolean delete(HyDweC hyDweC);
	Integer selectstcd(HyDweC hyDweC);
}
