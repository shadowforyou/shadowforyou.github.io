package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyPrexB;
@Mapper
public interface HyPrexBMapper {
	void add(HyPrexB hyPrexB);
	boolean delete(HyPrexB hyPrexB);
	Integer selectstcd(HyPrexB hyPrexB);
}
