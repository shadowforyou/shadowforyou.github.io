package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDzC;

@Mapper
public interface HyDzCMapper {
	void add(HyDzC hyDzC);
	boolean delete(HyDzC hyDzC);
	Integer selectstcd(HyDzC hyDzC);
}
