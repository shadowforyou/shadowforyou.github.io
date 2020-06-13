package com.poi.testpoi.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poi.testpoi.pojo.HyMtpE;
@Mapper
public interface HyMtpEMapper {
	void add(HyMtpE hyMtpE);
	boolean delete(HyMtpE hyMtpE);
	Integer selectstcd(@Param("stcd") String stcd,@Param("year") String year);
	Date selectOtd(HyMtpE hyMtpE);
}
