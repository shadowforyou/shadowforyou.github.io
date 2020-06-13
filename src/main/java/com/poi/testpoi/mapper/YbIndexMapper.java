package com.poi.testpoi.mapper;

import com.poi.testpoi.pojo.YbIndex;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YbIndexMapper {
    void add(YbIndex ybIndex);
    boolean delete(YbIndex ybIndex);
    Integer selectstcd(YbIndex ybIndex);
}
