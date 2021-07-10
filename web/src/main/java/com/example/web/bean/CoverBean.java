package com.example.web.bean;


import com.example.tools.converter.annotion.EnableUserInfoTransform;
import com.example.tools.converter.annotion.TransfCode;

@EnableUserInfoTransform
public class CoverBean {

    private String devCls;
    @TransfCode(valueFrom = "devCls",codeType = "aaa")
    private String devClsName;

    public String getDevCls() {
        return devCls;
    }

    public void setDevCls(String devCls) {
        this.devCls = devCls;
    }

    public String getDevClsName() {
        return devClsName;
    }

    public void setDevClsName(String devClsName) {
        this.devClsName = devClsName;
    }
}
