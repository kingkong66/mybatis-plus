package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Tai on 2018/10/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoService {

    @Autowired
    private IUsersService usersService;
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 根据id查询单个对象
     * @throws Exception
     */
    @Test
    public void getByUserId()throws Exception{
        Users users = new Users();
        users.setId(222l);
        Users user = usersService.getById(users);
        log.info("user的值为："+ new Gson().toJson(user));
    }

    @Test
    public void getOne()throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(new Users().setUsername("王宝").setId(222l));
       Users users =  usersService.getOne(queryWrapper);
       log.info("users的值为："+ new Gson().toJson(users));
    }

    @Test
    public void getAll()throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(new Users().setUsername("王宝"));
        List<Users> usersList = usersService.list(queryWrapper);
        log.info("usersList的值为：" + usersList);
    }

    @Test
    public void page()throws Exception{
        Page<Users> page = new Page();
        page.setCurrent(1);
        page.setSize(10);
        page.setTotal(10);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(new Users().setUsername("王宝"));
        queryWrapper.orderByAsc("id");
        IPage pages =  usersService.page(page,queryWrapper);
        List<Users> usersList = pages.getRecords();
        log.info("usersList的值为："+ new Gson().toJson(usersList));
    }

    @Test
    public void list()throws Exception{
        QueryWrapper<Users> qw = new QueryWrapper<Users>();
        qw.setEntity(new Users());
        qw.between("id","40","100"); //between val1 and val2
        //qw.ge("username","俞采萱");  //ge 大于 等于
        //qw.eq("username","俞采萱"); // and username='俞采萱'
       // qw.eq(StringUtils.isNotEmpty(""),"username",username); //参数不为空则 and username='俞采萱' 否则不加条件
      //  qw.orderByAsc("id"); //根据id降序
        List<Users> usersList =  usersService.list(qw);
        log.info("usersList的值为："+ new Gson().toJson(usersList));
    }


    /***
     * 获取用户信息
     * @throws Exception
     */
    @Test
    public void getByUser()throws Exception{
        Users users = usersService.getByUserId(222l);
        log.info("users的值为："+users);
    }

    /***
     * 获取用户金额
     * @throws Exception
     */
    @Test
    public void getByUserAmount()throws Exception{
        String result = usersService.selectUsersById(new Users());
        log.info("users的值为："+result);
    }

    @Test
    public void selectPageVo()throws Exception{
        String username = "邹心怡";
        Page<Users> page = new Page();
        page.setCurrent(1);
        page.setSize(10);
        page.setTotal(10);

        QueryWrapper<Users> qw = new QueryWrapper<Users>();
        qw.setEntity(new Users());
        qw.eq(StringUtils.isNotEmpty(username),"and username",username);
       // qw.between("id","40","100");
        IPage<Users> pages = usersService.selectPageVo(page,qw);
        List<Users> usersList = pages.getRecords();
        log.info("usersList的值为："+usersList);
    }

    @Test
    public void listAll()throws Exception{
        Users uesrs = new Users();
        uesrs.setUsername("王宝");
        uesrs.setId(222l);
        List<Users> usersList = usersService.listAll(uesrs);
        log.info("usersList的值为：" + usersList);
    }

}
