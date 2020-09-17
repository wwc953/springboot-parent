package com.example.web;

import com.alibaba.fastjson.JSON;
import com.example.web.yaml.FileInfo;
import com.example.web.yaml.YamlLoad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/17 16:02
 */
@SpringBootTest(classes = FirstApplication.class)
@AutoConfigureMockMvc
public class YamlLoadTest {

    @Autowired
    YamlLoad yamlLoad;

    @Test
    void test() throws Exception {
        Map<String, List<FileInfo>> basic = yamlLoad.loadFile("basic");
        System.out.println(JSON.toJSONString(basic));
    }
}
