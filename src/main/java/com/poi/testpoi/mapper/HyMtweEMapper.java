package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyMtweE;
@Mapper
public interface HyMtweEMapper {
	void add(HyMtweE hyMtweE);
	boolean delete(HyMtweE hyMtweE);
	Integer selectstcd(HyMtweE hyMtweE);
}
