package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Tai on 2018/11/20.
 */
@Getter
@Setter
public class Users implements Serializable{
    private String user_id;
    private String user_name;
    private String pass_word;
    private String remark;
}
