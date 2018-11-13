package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zac.jin
 * @since 2018-10-24
 */
public interface UsersMapper extends BaseMapper<Users> {

    IPage<Users> selectPageVo(Page page,  @Param("ew") QueryWrapper<Users> queryWrapper);

  //  @Select("select id,username,phone_no,id_no,channel,create_time from users where id = #{id}")
   // Users getByUserId(@Param("id")Long id);

    Users getByUserId(@Param("id")Long id);

    List<Users> getByUserInfo(Users users);

    List<Users> getByUserAmount(Users users);

    List<Users> listAll(Users users);



}
