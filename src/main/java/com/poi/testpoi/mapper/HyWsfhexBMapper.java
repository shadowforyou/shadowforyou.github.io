package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyWsfhexB;
@Mapper
public interface HyWsfhexBMapper {
	void add(HyWsfhexB hyWsfhexB);
	boolean delete(HyWsfhexB hyWsfhexB);
	Integer selectstcd(HyWsfhexB hyWsfhexB);
}
