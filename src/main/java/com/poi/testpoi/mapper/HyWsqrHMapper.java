package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyWsqrH;
@Mapper
public interface HyWsqrHMapper {
	void add(HyWsqrH hyWsqrH);
	boolean delete(HyWsqrH hyWsqrH);
	Integer selectstcd(HyWsqrH hyWsqrH);
}
