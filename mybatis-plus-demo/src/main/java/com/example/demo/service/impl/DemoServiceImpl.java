package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.demo.entity.Demo;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.IDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zac.jin
 * @since 2018-11-13
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List demoList(Map<String, Object> map) {
        return demoMapper.selectByMap(map);
    }

    @Override
    public Demo getDemoById(Long id) {
        return demoMapper.getDemoById(id);
    }

    @Override
    public List<Demo> selectListByWrapper(Wrapper wrapper) {
        return demoMapper.selectListByWrapper(wrapper);
    }
}
