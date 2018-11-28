package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Tai on 2018/11/20.
 */
@Getter
@Setter
public class RedisEntry implements Serializable{

    private static final long serialVersionUID = 2188542845707969627L;

    private String id;
    private String name;
    private String remark;

    public RedisEntry(){}

    public RedisEntry(String id,String name,String remark){
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

}
