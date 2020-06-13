package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMtqE;
@Mapper
public interface HyMtqEMapper {
	void add(HyMtqE hyMtqE);
	boolean delete(HyMtqE hyMtqE);
	Integer selectstcd(HyMtqE hyMtqE);
}
