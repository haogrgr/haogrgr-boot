package com.haogrgr.boot.dal.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class TestModel implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * id : 主键
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * name : 名字
     **/
    private String name;
    /**
     * age : 年龄
     **/
    private Integer age;
    /**
     * modify_time : 修改时间
     **/
    private Date modifyTime;
    /**
     * create_time : 创建时间
     **/
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", modifyTime=" + modifyTime +
                ", createTime=" + createTime +
                '}';
    }
}
