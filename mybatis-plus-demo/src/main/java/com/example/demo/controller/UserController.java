package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.contact.Result;
import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/demo")
@Slf4j
public class UserController {

    @Autowired
    private IUsersService usersService;


    @GetMapping("getUserById")
    @ResponseBody
    public Users getUserById(@PathVariable Long id)throws Exception{
        if(id == null){
            throw new NullPointerException("参数为空!");
        }
        Users users =  usersService.getById(new Users().setId(id));
        return users;
    }

    @GetMapping("/getAll")
    public String getAll()throws Exception{
        Users users = new Users();
        users.setId(222l);
        usersService.getById(users);
        List<Users> usersList = usersService.list(new QueryWrapper<Users>().setEntity(users));
        log.info("usersList的值为：" + new Gson().toJson(usersList.get(0)));
        return Result.success(usersList);
    }

    @GetMapping("/getByUser")
    public String getByUser()throws Exception{
        Users users = usersService.getByUserId(222l);
        return Result.success(users);
    }

    @GetMapping("/test")
    public IPage<Users> test() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.setEntity(new Users().setUsername("这个夏天"));
        return usersService.page(new Page<Users>(1, 5), queryWrapper);
    }

    @GetMapping("/page")
    public IPage page(Page page, boolean listMode) {
        if (listMode) {
            // size 小于 0 不在查询 total 及分页，自动调整为列表模式。
            // 注意！！这个地方自己控制好！！
            page.setSize(-1);
        }
        return usersService.page(page, null);
    }


    @GetMapping("/selectPageVo")
    public IPage<Users> selectPageVo() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.setEntity(new Users().setId(222l));
        return usersService.selectPageVo(new Page<Users>(1, 10), queryWrapper);
    }

   @PostMapping("testCallable")
   public Callable<String> selectUsersById(@RequestBody Users users)throws Exception{
        System.out.println(System.currentTimeMillis());
        System.out.println("线程名称1："+Thread.currentThread().getName());
        Long statTime = new Date().getTime();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("线程名称2："+Thread.currentThread().getName());
                String result = usersService.selectUsersById(users);
                return result;
            }
        };
        log.info("selectUsersById 执行耗时："+ ((new Date().getTime()) - statTime));
        return callable;
    }

    @PostMapping("testAsync")
    public String testAsync(@RequestBody Users users)throws Exception{
        System.out.println(System.currentTimeMillis());
        System.out.println("线程名称1："+Thread.currentThread().getName());
        Long statTime = new Date().getTime();
        String result = usersService.selectUsersById(users);
        log.info("selectUsersById 执行耗时："+ ((new Date().getTime()) - statTime));
        return result;
    }
}
