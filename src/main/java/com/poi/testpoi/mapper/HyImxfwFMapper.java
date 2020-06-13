package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyImxfwF;
@Mapper
public interface HyImxfwFMapper {
	void add(HyImxfwF hyImxfwF);
	boolean delete(HyImxfwF hyImxfwF);
	Integer selectstcd(HyImxfwF hyImxfwF);
}
