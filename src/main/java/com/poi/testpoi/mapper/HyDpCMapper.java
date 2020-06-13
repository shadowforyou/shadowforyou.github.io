package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poi.testpoi.pojo.HyDpC;

@Mapper
public interface HyDpCMapper {
	void add(HyDpC hyDpC);
	boolean delete(HyDpC hyDpC);
	Integer selectstcd(@Param("stcd") String stcd,@Param("year") String year);
}
