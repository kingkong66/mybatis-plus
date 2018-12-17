package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.contact.Result;
import com.example.demo.common.contact.mongodb.Pagination;
import com.example.demo.entity.Users;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tai on 2018/11/20.
 */
@RestController
@RequestMapping("mongodb")
@Slf4j
public class MongoDBController {

    @Autowired
    private MongoTemplate mongoTemplate; 

    /**
     * http://localhost:8080/mongodb/selectOne
     * @return
     * @throws Exception
     */
    @GetMapping("selectOne")
    public String selectOne()throws Exception{
        List<Users> usersList = mongoTemplate.findAll(Users.class);
        log.info("usersList的值为："+ JSON.toJSON(usersList));

        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is("Ts0000001"));
        Users user = mongoTemplate.findOne(query,Users.class);
        log.info("user的值为："+ JSON.toJSON(user));
        return Result.success(user);
    }

    /**
     * http://localhost:8080/mongodb/saveOne
     * @return
     * @throws Exception
     */
    @GetMapping("saveOne")
    public String saveOne()throws Exception{
        Users users = new Users();
        users.setUser_id("T00000000002");
        users.setPass_word("123456");
        users.setUser_name("admin");
        users.setRemark("添加admin用户");
        mongoTemplate.insert(users);
        return Result.success();
    }

    /**
     * http://localhost:8080/mongodb/updateOne
     * @return
     * @throws Exception
     */
    @GetMapping("updateOne")
    public String updateOne()throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is("Ts0000001"));
        query.addCriteria(Criteria.where("user_name").is("root"));

        Update update = new Update();
        update.set("user_name", "root_01");
        update.set("pass_word", "root");
        update.set("remark","root用户修改成 root_01");

         mongoTemplate.findAndModify(query,update,Users.class);
        return Result.success();
    }

    /**
     * http://localhost:8080/mongodb/updateOne2
     * @return
     * @throws Exception
     */
    @GetMapping("updateOne2")
    public String updateOne2()throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is("Ts0000001").and("user_name").is("root_01"));
        Update update = new Update();
        update.set("user_name", "root");
        update.set("pass_word", "123456");
        update.set("remark","root_01 用户修改成 root");
        UpdateResult result =  mongoTemplate.updateFirst(query,update,Users.class);
        return Result.success(result);
    }

    /**
     * http://localhost:8080/mongodb/remove
     * @return
     * @throws Exception
     */
    @GetMapping("remove")
    public String remove()throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is("Ts00000012").and("user_name").is("root"));
        DeleteResult result =  mongoTemplate.remove(query,Users.class);
        return Result.success(result);
    }


    /**
     * http://localhost:8080/mongodb/getPage
     * @return
     */
    @GetMapping("getPage")
    @ResponseBody
    public Pagination<Users> getPage() {
        int pageNo = 1, pageSize = 10;
        Query query = new Query();
        long totalCount = mongoTemplate.count(query, Users.class);
        Pagination<Users> page = new Pagination<Users>(pageNo, pageSize, totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(pageSize);// 从skip开始,取多少条记录
        List<Users> datas = mongoTemplate.find(query,Users.class);
        page.setDatas(datas);
        return page;
    }
}
