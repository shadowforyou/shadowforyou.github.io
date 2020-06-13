package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poi.testpoi.pojo.HyYrpF;
@Mapper
public interface HyYrpFMapper {
	void add(HyYrpF hyYrpF);
	boolean delete(HyYrpF hyYrpF);
	Integer selectstcd(@Param("stcd") String stcd,@Param("year") String year);
}
