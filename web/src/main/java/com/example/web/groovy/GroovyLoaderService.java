package com.example.web.groovy;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.ant.Groovy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description: TODO 前台保存脚本语句，后台读取执行
 * @author: wangwc
 * @date: 2020/10/14 15:03
 */
@Component
public class GroovyLoaderService {
    private Object lock = new Object();
    private GroovyShell shell = new GroovyShell();
    private static final ConcurrentMap<String, Script> SCRIPT_CACHE = new ConcurrentHashMap<>();

    public Object parseAndInvoke(String scriptText, String functionName, Map params) {
        Script script = SCRIPT_CACHE.get(functionName);
        if (script == null) {
            synchronized (lock) {
                SCRIPT_CACHE.putIfAbsent(functionName, shell.parse(scriptText));
            }
            script = SCRIPT_CACHE.get(functionName);
        }
        Object obj = script.invokeMethod(functionName, params);
        return obj;
    }

    public void cleanCache(String functionName) {
        SCRIPT_CACHE.remove(functionName);
    }

    public void cleanCache() {
        SCRIPT_CACHE.clear();
    }

    public String createScriptText(String functionName) {
        return "def " + functionName + "(Map map){ return map.get(\"key\") }";
    }

    public static void main(String[] args) {
        String scriptText = "def hello(Map map){ return map.get(\"key\") }";
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(scriptText);
        Map<String, String> params = new HashMap<>();
        params.put("key", "111");
        Object result = script.invokeMethod("helloMethod", params);
        System.out.println(result);
    }

}
