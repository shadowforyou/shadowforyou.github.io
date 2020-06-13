package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poi.testpoi.pojo.HyObqG;
@Mapper
public interface HyObqGMapper {
	void add(HyObqG hyObqG);
	Boolean delete(HyObqG hyObqG);
	Integer selectstcd(HyObqG hyObqG);
}
