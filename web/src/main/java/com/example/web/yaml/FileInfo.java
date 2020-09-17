package com.example.web.yaml;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/17 15:50
 */
public class FileInfo implements Serializable {

    private String name;
    private String key;
    private String method;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
