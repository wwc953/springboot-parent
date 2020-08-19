package com.example.web.bean;

import com.example.web.config.LongJsonDeserializer;
import com.example.web.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @Description: User对象
 * @author: wangwc
 * @date: 2020/8/9 21:44
 */
public class User implements Serializable {
    //自定义序列化反序列化
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;
    private String address;

    public User() {
    }

    public User(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
