package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.config.SnowFlake;
import com.example.demo.entity.Demo;
import com.example.demo.service.IDemoService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.util.*;
import java.util.function.Function;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zac.jin
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    private IDemoService demoService;

    private static SnowFlake snowFlake = new SnowFlake(2, 3);

    /**
     * http://localhost:8080/demo/add
     * @return
     */
    @GetMapping("add")
    public Object add(){
       Demo demo = new Demo(snowFlake.nextId(),"麻花藤",56l,1l,"13012356231",new Date());
        //boolean result = demo.insert();
      // log.info("result {} :",result);
       return demoService.save(demo);
    }

    /**
     * http://localhost:8080/demo/update
     * @return
     */
    @GetMapping("update")
    public Object update(){
        Demo demo = new Demo().selectById(259716968264262000l);
        log.info("demo的值为："+ new Gson().toJson(demo));

       boolean flag =  demoService.updateById(new Demo(259716968264262000l,"麻花藤",12l,12l,"13812545545",new Date()));
        return flag;
    }

    /**
     *  http://localhost:8080/demo/delete
     * @return
     */
    @GetMapping("delete")
    public Object delete(){
        Demo demo = new Demo().selectById(4);
        log.info("demo的值为："+ new Gson().toJson(demo));
        return demo.deleteById(demo.getId());
    }

    /**
     * http://localhost:8080/demo/list
     * @return
     */
    @GetMapping("list")
    public Object list(){
        QueryWrapper wq = new QueryWrapper();
        wq.setEntity(new Demo().setType(12l));
        return new Gson().toJson(demoService.list(wq));
    }

    /**
     * http://localhost:8080/demo/ListPage
     * @return
     */
    @GetMapping("ListPage")
    public Object ListPage(){
      IPage<Demo> pages = demoService.page(new Page<Demo>(),new QueryWrapper<Demo>().orderByAsc("create_time"));
      return new Gson().toJson(pages);
    }

    /**
     * http://localhost:8080/demo/ListPage1
     * @return
     */
    @GetMapping("ListPage1")
    public Object ListPage1(){
        IPage<Demo> pages = demoService.page(new Page<Demo>(1,3),new QueryWrapper<Demo>());
        return new Gson().toJson(pages);
    }

    /**
     * http://localhost:8080/demo/selectDemoList
     * @return
     */
    @GetMapping("selectDemoList")
    public Object selectDemoList(){
        QueryWrapper<Demo> wq = new QueryWrapper<Demo>();
        wq.eq("name","麻花藤");
        wq.or();
        wq.between("id",1l,12l);
       return new Gson().toJson(demoService.list(wq));
    }

    /**
     *  http://localhost:8080/demo/demoList
     * @return
     */
    @GetMapping("demoList")
    public Object demoList(){
        Map<String,Object> map = new HashMap(4);
        map.put("name","麻花藤");
        List<Demo> demoList = demoService.demoList(map);
        return  new Gson().toJson(demoList);
    }

    /**
     * http://localhost:8080/demo/getDemoById
     * @return
     */
    @GetMapping("getDemoById")
    public Object getDemoById(){
      Demo demo = demoService.getDemoById(11l);
        return new Gson().toJson(demo);
    }

    /**
     * http://localhost:8080/demo/select_wrapper
     */
    @GetMapping("/select_wrapper")
    public Object getUserByWrapper() {
        return demoService.selectListByWrapper(new QueryWrapper<Demo>()
                .lambda().like(Demo::getName, "朱")
                .or(e -> e.like(Demo::getName, "刘")));
    }


    /**
     * 测试事物
     * http://localhost:8080/demo/test_transactional<br>
     * 访问如下并未发现插入数据说明事物可靠！！<br>
     *
     * <br>
     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
     * 需要事物的方法加上 @Transactional 必须的哦！！
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test_transactional")
    public void testTransactional() {
        demoService.save(new Demo(259716968264262111l,"道长",28l,3l,"13786456254",new Date()));
      //  System.out.println(" 这里手动抛出异常，自动回滚数据");
       // throw new RuntimeException();
    }

}
