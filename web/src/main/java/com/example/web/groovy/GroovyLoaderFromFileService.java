package com.example.web.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: TODO 读取Groovy脚本
 * @author: wangwc
 * @date: 2020/10/14 15:38
 */
@Component
public class GroovyLoaderFromFileService {
    public Object parseAndInvokeByGroovyShell(String functionName, List list) {
        String filePath = "web/src/main/resources/groovy/" + functionName + ".groovy";
        Binding binding = new Binding();
        binding.setProperty("params", list);
        GroovyShell shell = new GroovyShell(binding);
        Object obj = null;
        try {
            obj = shell.evaluate(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Object parseAndInvokeByGroovyScriptEngine(String functionName, List params) {
        String allFiles = "web/src/main/resources/groovy/";
        Object obj = null;
        try {
            Binding binding = new Binding();
            binding.setProperty("params", params);
            GroovyScriptEngine engine = new GroovyScriptEngine(allFiles);
            obj = engine.run(functionName + ".groovy", binding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
//        String filePath = "web/src/main/resources/groovy/helloMethod.groovy";
//        //绑定参数
//        Binding binding = new Binding();
        List<String> params = new ArrayList<>();
        params.add("1");
        params.add("2");
//        binding.setProperty("params", params);
//
//        GroovyShell shell = new GroovyShell(binding);
//        Object result = null;
//        try {
//            result = shell.evaluate(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);

//        GroovyLoaderFromFileService loader = new GroovyLoaderFromFileService();
//        Object invoke = loader.parseAndInvokeByGroovyShell("helloMethod", params);
//        Object invoke = loader.parseAndInvokeByGroovyScriptEngine("helloMethod", params);
//        System.out.println(invoke);

    }
}
