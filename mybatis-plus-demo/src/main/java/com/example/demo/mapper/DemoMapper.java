package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.demo.entity.Demo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zac.jin
 * @since 2018-11-13
 */
public interface DemoMapper extends BaseMapper<Demo> {

    @Select("select * from demo where id = #{id}")
    Demo getDemoById(Long id);

    List<Demo> selectListByWrapper(@Param("ew") Wrapper wrapper);

}
