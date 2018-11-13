package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zac.jin
 * @since 2018-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Demo extends Model<Demo> {

    /**
     * 主键Id
     */
    @TableField("ID")
    private Long id;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 年龄
     */
    @TableField("AGE")
    private Long age;

    /**
     * 类型
     */
    @TableField("TYPE")
    private Long type;

    /**
     * 手机号码
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 日期
     */
    @TableField("CREATE_TIME")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Demo(Long id,String name,Long age,Long type,String phone,Date createTime){
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.phone = phone;
        this.createTime = createTime;
    }

    public Demo(){
        super();
    }
}
