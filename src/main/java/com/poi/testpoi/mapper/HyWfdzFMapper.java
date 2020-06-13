package com.poi.testpoi.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.poi.testpoi.pojo.HyWfdzF;
@Mapper
public interface HyWfdzFMapper {
	void add(HyWfdzF hyWfdzF);
	boolean delete(HyWfdzF hyWfdzF);
	Integer selectstcd(HyWfdzF hyWfdzF);
}
