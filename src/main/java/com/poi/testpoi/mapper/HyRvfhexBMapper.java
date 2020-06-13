package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyRvfhexB;
@Mapper
public interface HyRvfhexBMapper {
	void add(HyRvfhexB hyRvfhexB);
	boolean delete(HyRvfhexB hyRvfhexB);
	Integer selectstcd(HyRvfhexB hyRvfhexB);
}
