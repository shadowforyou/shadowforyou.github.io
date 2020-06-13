package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMtcsE;

@Mapper
public interface HyMtcsEMapper {
	void add(HyMtcsE hyMtcsE);
	boolean delete(HyMtcsE hyMtcsE);
	Integer selectstcd(HyMtcsE hyMtcsE);
}
