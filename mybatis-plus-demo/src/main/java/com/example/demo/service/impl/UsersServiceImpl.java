package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zac.jin
 * @since 2018-10-24
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    public IPage<Users> selectPageVo(Page<Users> page, QueryWrapper<Users> queryWrapper) {
        return usersMapper.selectPageVo(page, queryWrapper);
    }

    @Override
    public Users getByUserId(Long id) {
        return usersMapper.getByUserId(id);
    }



    public List<Users> listAll(Users users){
        return usersMapper.listAll(users);
    }

    @Override
    @Async
    public String selectUsersById(Users users)throws Exception{
        System.out.println("线程名称2："+Thread.currentThread().getName());
       if(StringUtils.isEmpty(users)){
            throw new NullPointerException("参数users为空!");
        }
       List<Users> usersList = new ArrayList<>();
       List<Users> userInfo = usersMapper.getByUserInfo(users);
       List<Users> userAmount = usersMapper.getByUserAmount(users);
       usersList.addAll(userInfo);
       usersList.addAll(userAmount);
       String result = new Gson().toJson(usersList);
       return result;
    }


}
