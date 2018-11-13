package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zac.jin
 * @since 2018-10-24
 */
public interface IUsersService extends IService<Users> {

    IPage<Users> selectPageVo(Page<Users> page, QueryWrapper<Users> queryWrapper);

    Users getByUserId(Long id);

    List<Users> listAll(Users users);

    String selectUsersById(Users users)throws Exception;
}
