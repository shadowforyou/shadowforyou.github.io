package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyDqC;

@Mapper
public interface HyDqCMapper {
	void add(HyDqC hyDqC);
	boolean delete(HyDqC hyDqC);
	Integer selectstcd(HyDqC hyDqC);
}
