package com.poi.testpoi.mapper;

import com.poi.testpoi.pojo.HyStscA;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HyStscAMapper {
    void add(HyStscA hyStscA);
    boolean delete(HyStscA hyStscA);
    Integer selectstcd(HyStscA hyStscA);
    Integer selectstcd(@Param("stcd") String stcd);
}
