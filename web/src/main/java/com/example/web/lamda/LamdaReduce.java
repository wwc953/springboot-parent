package com.example.web.lamda;

import java.util.Arrays;

/**
 * @Description: TODO Lamda map参数替换, reduce数据拼接
 * @author: wangwc
 * @date: 2020/10/13 14:37
 */
public class LamdaReduce {

    public static void main(String[] args) {

        String[] strry = new String[]{};
        String[] strryOne = new String[]{"my_id"};
        String[] strrytwo = new String[]{"my_id", "my_name", "my_id2", "my_name2"};
        char separator = ',';
        strAppend(strry, separator);//null
        strAppend(strryOne, separator);//my_id
        strAppend(strrytwo, separator);//my_id,my_name,my_id2,my_name2

        separator = '?';
        strAppendParams(strry, separator);//null
        strAppendParams(strryOne, separator);//?
        strAppendParams(strrytwo, separator);//?,?,?,?
    }

    public static void strAppend(String[] args, char separator) {
        //orElse 当args长度为0时，返回null
        String result = Arrays.stream(args).reduce((v1, v2) -> v1 + "," + v2).orElse(null);
        System.out.println(result);
    }

    public static void strAppendParams(String[] args, char separator) {
        String result = Arrays.stream(args).map(v -> "?").reduce((v1, v2) -> v1 + "," + v2).orElse(null);
        System.out.println(result);
    }


}
