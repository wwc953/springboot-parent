package com.example.web.groovy;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description: TODO 规则引擎执行类
 * @author: wangwc
 * @date: 2020/10/14 15:03
 */
@Component
public class GroovyLoaderService {
    private Object lock = new Object();
    private GroovyShell shell = new GroovyShell();
    /**
     * JDK7 之前：防止PermGen永久代内存溢出,
     * JDK8 防止Metaspace内存溢出, -XX:MaxMetaspaceSize=256m 最大值
     * <p>
     * 原因：
     *  每次groovy编译脚本后，都会缓存该脚本的Class对象，下次编译该脚本时，会优先从缓存中读取，这样节省掉编译的时间。
     *  这个缓存的Map由GroovyClassLoader持有，key是脚本的类名，而脚本的类名在不同的编译场景下
     * （从文件读取脚本/从流读取脚本/从字符串读取脚本）其命名规则不同，当传入text时，class对象的命名规则为：
     *  "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy"
     *  因此，每次编译的对象名都不同，都会在缓存中添加一个class对象，导致class对象不可释放，
     *  随着次数的增加，编译的class对象将PERM区撑满.
     */
    private static final ConcurrentMap<String, Script> SCRIPT_CACHE = new ConcurrentHashMap<>();

    public Object parseAndInvoke(String scriptText, String functionName, Map params) {
        Script script = SCRIPT_CACHE.get(functionName);
        if (script == null) {
            synchronized (lock) {
                SCRIPT_CACHE.putIfAbsent(functionName, shell.parse(scriptText));
            }
            script = SCRIPT_CACHE.get(functionName);
        }
        Object result = script.invokeMethod(functionName, params);
        return result;
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

    public String createScriptTextFromMap(String functionName) {
        return "def " + functionName + "(Map map){  map.put(\"result\",map.get(\"key\")+10) }";
    }

    public static void main(String[] args) {
//        String scriptText = "def hello(Map map){ return map.get(\"key\") }";
//        GroovyShell shell = new GroovyShell();
//        Script script = shell.parse(scriptText);
//        Map<String, String> params = new HashMap<>();
//        params.put("key", "111");
//        Object result = script.invokeMethod("helloMethod", params);
//        System.out.println(result);

        String scriptText = "def aa(Map map){ map.put(\"result\",map.get(\"key\")+10) }";
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(scriptText);
        Map<String, String> params = new HashMap<>();
        params.put("key", "99");
        script.invokeMethod("aa", params);
        System.out.println(params.get("result"));

    }

}
