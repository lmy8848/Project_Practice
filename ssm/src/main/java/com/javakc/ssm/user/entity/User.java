package com.javakc.ssm.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author NJQ-PC
 */
@Getter
@Setter
@ToString
public class User {
    //主键ID
    private int id;
    //姓名
    private String name;
    //人员性别
    private int gender;
    //出生日期
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;
    //家庭地址
    private String address;
}
