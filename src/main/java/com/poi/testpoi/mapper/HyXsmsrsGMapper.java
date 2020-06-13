package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyXsmsrsG;
@Mapper
public interface HyXsmsrsGMapper {
	void add(HyXsmsrsG hyXsmsrsG);
	boolean delete(HyXsmsrsG hyXsmsrsG);
	Integer selectstcd(HyXsmsrsG hyXsmsrsG);
}
