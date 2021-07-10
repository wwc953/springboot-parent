package com.example.web.controller;

import com.example.tools.bean.ResponseResult;
import com.example.web.bean.CoverBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cover")
public class CoverTestController {
    @PostMapping("/test")
    public ResponseResult<CoverBean> responseResult() {
        ResponseResult<CoverBean> responseResult = new ResponseResult<>();
        CoverBean coverBean = new CoverBean();
        coverBean.setDevCls("01");
        responseResult.setData(coverBean);
        return responseResult;
    }

    @PostMapping("/testList")
    public ResponseResult<List<CoverBean>> responseResultList() {
        ResponseResult<List<CoverBean>> responseResult = new ResponseResult<>();
        List<CoverBean> list = new ArrayList<>();
        CoverBean coverBean = new CoverBean();
        coverBean.setDevCls("01");
        list.add(coverBean);

        CoverBean coverBean2 = new CoverBean();
        coverBean2.setDevCls("02");
        list.add(coverBean2);

        responseResult.setData(list);
        return responseResult;
    }
}
