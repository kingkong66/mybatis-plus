package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.demo.entity.Demo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zac.jin
 * @since 2018-11-13
 */
public interface IDemoService extends IService<Demo> {

    List demoList(Map<String,Object> map);

    Demo getDemoById(Long id);

    List<Demo> selectListByWrapper(Wrapper wrapper);

}
