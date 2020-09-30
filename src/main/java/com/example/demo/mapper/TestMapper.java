package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TestMapper {

    int saveTableTest(@Param("table") String table,
                      @Param("key") String key,
                      @Param("value") String value);

    int saveTest(@Param("key") String key,
                 @Param("value") String value);

    int updateTest(@Param("key") String key,
                   @Param("value") String value,
                   @Param("id") Integer id);

    Map<String, Object> getTest(@Param("id") Integer id);

    int delTest(@Param("id") Integer id);
}
